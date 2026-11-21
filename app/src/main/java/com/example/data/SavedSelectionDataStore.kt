package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.model.SavedSelectionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.showroomDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "al_noor_showroom_prefs"
)

class SavedSelectionDataStore(private val context: Context) {

    private val savedEntriesKey = stringSetPreferencesKey("saved_selection_entries")

    val savedItemsFlow: Flow<Map<String, SavedSelectionItem>> =
        context.showroomDataStore.data.map { prefs ->
            val rawSet = prefs[savedEntriesKey] ?: emptySet()
            rawSet.mapNotNull { decodeEntry(it) }
                .associateBy { it.productId }
        }

    suspend fun saveOrUpdate(item: SavedSelectionItem) {
        context.showroomDataStore.edit { prefs ->
            val current = (prefs[savedEntriesKey] ?: emptySet())
                .mapNotNull { decodeEntry(it) }
                .associateBy { it.productId }
                .toMutableMap()
            current[item.productId] = item
            prefs[savedEntriesKey] = current.values.map { encodeEntry(it) }.toSet()
        }
    }

    suspend fun remove(productId: String) {
        context.showroomDataStore.edit { prefs ->
            val current = (prefs[savedEntriesKey] ?: emptySet())
                .mapNotNull { decodeEntry(it) }
                .filterNot { it.productId == productId }
            prefs[savedEntriesKey] = current.map { encodeEntry(it) }.toSet()
        }
    }

    suspend fun clearAll() {
        context.showroomDataStore.edit { prefs ->
            prefs.remove(savedEntriesKey)
        }
    }

    private fun encodeEntry(item: SavedSelectionItem): String {
        val safeFinish = item.selectedFinishName.replace("|", " ")
        return "${item.productId}|$safeFinish|${item.quantity}"
    }

    private fun decodeEntry(raw: String): SavedSelectionItem? {
        val parts = raw.split("|")
        if (parts.size < 3) return null
        val id = parts[0]
        val finish = parts[1]
        val qty = parts[2].toIntOrNull()?.coerceAtLeast(1) ?: 1
        return SavedSelectionItem(
            productId = id,
            selectedFinishName = finish,
            quantity = qty
        )
    }
}
