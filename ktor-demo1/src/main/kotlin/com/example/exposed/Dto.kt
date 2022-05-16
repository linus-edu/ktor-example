import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(val name: String, val price: Int, val inventory: Int)
