package libraryinfo.presentation.internal

import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.TreeItem

class Pair(var key: String, var value: String) : RecursiveTreeObject<Pair>()

class ReadOnlyPairTableHelper() {
    private val table = JFXTreeTableView<Pair>()
    private val contentList = FXCollections.observableArrayList<Pair>()
    private val keyColumn = JFXTreeTableColumn<Pair, String>()
    private val valueColumn = JFXTreeTableColumn<Pair, String>()


    fun addPair(key: String, value: String): ReadOnlyPairTableHelper {
        contentList.add(Pair(key, value))
        return this
    }

    fun addMap(dict: Map<String, String>): ReadOnlyPairTableHelper {
        for ((key, value) in dict) {
            contentList.add(Pair(key, value))
        }
        return this
    }

    constructor(vararg contentList: Pair) : this() {
        this.contentList.addAll(*contentList)
    }

    init {
        keyColumn.text = "键"
        valueColumn.text = "值"
        keyColumn.setCellValueFactory { cellData -> SimpleStringProperty(cellData.value.value.key) }
        valueColumn.setCellValueFactory { cellData -> SimpleStringProperty(cellData.value.value.value) }
    }


    fun create(): JFXTreeTableView<*> {
        val root = RecursiveTreeItem(contentList) { it.children }
        table.root = root
        table.columns.setAll(keyColumn, valueColumn)
        table.isEditable = false
        table.isShowRoot = false
        return table
    }

    companion object {

        fun start(vararg contentList: Pair): ReadOnlyPairTableHelper {
            return ReadOnlyPairTableHelper(*contentList)
        }

        fun start(): ReadOnlyPairTableHelper {
            return ReadOnlyPairTableHelper()
        }
    }


}

