package tpm.yu.mol.share.bin

import androidx.fragment.app.FragmentActivity

class SaData(val viewType: BaseData) {
    /* ************************************************************
     * Classes
     ************************************************************ */
    enum class Direction {
        VERTICAL, HORIZONTAL
    }

    enum class Quantity {
        ONE, MANY
    }

    sealed class BaseData(
        open var quantity: Quantity
    )

    /* ************************************************************
     * List
     ************************************************************ */
    sealed class ListData(open var direction: Direction) : BaseData(quantity = Quantity.MANY)

    class List(override var direction: Direction) : ListData(direction = direction)

    class Grid(override var direction: Direction, val count: Int = 3) :
        ListData(direction = direction)

    class Pager(override var direction: Direction, val fragmentActivity: FragmentActivity) :
        ListData(direction = direction)

    /* ************************************************************
     * One
     ************************************************************ */
    sealed class OneType(open var direction: Direction) : BaseData(quantity = Quantity.ONE)
}