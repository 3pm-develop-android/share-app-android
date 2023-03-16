package tpm.yu.mol.share.bin

class SaView(val viewType: ViewType) {
    enum class Direction {
        VERTICAL, HORIZONTAL
    }

    sealed class ViewType(
        open var direction: Direction = Direction.VERTICAL
    )
    class List(override var direction: Direction) : ViewType(direction = direction)
    class Grid(override var direction: Direction, val count: Int = 3) :
        ViewType(direction = direction)
}