package libraryinfo.presentation.helpui

import com.jfoenix.controls.*
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyCode
import libraryinfo.presentation.internal.*

class StringRecursiveObject(val value: String): RecursiveTreeObject<StringRecursiveObject>() {
    val valueProperty: StringProperty = SimpleStringProperty(value)
}

class CRUDSampleController : UiController {
    lateinit var tfSearch: JFXTextField
    lateinit var tcName: JFXTreeTableColumn<StringRecursiveObject, String>
    lateinit var btnAdd: JFXButton
    lateinit var btnModify: JFXButton
    lateinit var btnDelete: JFXButton
    lateinit var tableEmployee: JFXTreeTableView<StringRecursiveObject>

    private val employeeInfoModels = FXCollections.observableArrayList<StringRecursiveObject>()

    val selected: String?
        get() {
            return try {
                tableEmployee.selectionModel.selectedItem.value.valueProperty.get()

            } catch (e: Exception) {
                PromptDialogHelper("未选择！", "请先选择一个职员！")
                    .addCloseButton("好", "CHECK", null)
                    .createAndShow()
                null
            }

        }

    private fun search() {
        val str = tfSearch.text
        if (str.isEmpty()) {
            updateItems()
        } else {
            updateItems()
//            val queryVo = UserAccountQueryVo()
//            queryVo.addQueryVoForAllEmployeePosition(SpecificUserAccountQueryVo())
//            val employee = blService.query(queryVo)
//            if (employee != null) {
//                employeeInfoModels.clear()
//                for (employeeVo in employee!!) {
//                    if (employeeVo.getId().contains(str)
//                        || employeeVo.getName().contains(str)
//                        || employeeVo.getPosition().toString().contains(str)
//                        || employeeVo.getState().toString().contains(str)
//                    ) {
//                        employeeInfoModels.add(EmployeeInfoModel(employeeVo))
//                    }
//                }
//
//            } else {
//                PromptDialogHelper("未查到匹配信息！", "")
//                    .addCloseButton("好", "CHECK", null)
//                    .createAndShow()
//            }
        }
    }

    fun initialize() {
        initTable()
        updateItems()
    }

    private fun updateItems() {
        employeeInfoModels.clear()

        employeeInfoModels.add(StringRecursiveObject("测试数据"))
        employeeInfoModels.add(StringRecursiveObject(tfSearch.text))
//        val queryVo = UserAccountQueryVo()
//        queryVo.addQueryVoForAllEmployeePosition(SpecificUserAccountQueryVo())
//        val result = blService.query(queryVo)
//        for (employeeVo in result) {
//            employeeInfoModels.add(EmployeeInfoModel(employeeVo))
//        }

    }

    private fun initTable() {
        tfSearch.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
//                cbPosition.setValue(null)
                search()
            }
        }

        tcName.setCellValueFactory { it.value.value.valueProperty }

//        cbPosition.getItems().addAll(FXCollections.observableArrayList(EmployeePosition.values()))
//        cbPosition.getSelectionModel().selectedItemProperty().addListener { observable, oldValue, newValue ->
//            if (newValue == null) {
//
//            } else {
//                tfSearch!!.text = ""
//                employeeInfoModels.clear()
//                val queryVo = UserAccountQueryVo()
//                queryVo.addQueryVoForOneEmployeePosition(newValue, SpecificUserAccountQueryVo())
//                val result = blService.query(queryVo)
//                for (employeeVo in result) {
//                    employeeInfoModels.add(EmployeeInfoModel(employeeVo))
//                }
//            }
//        }
        val root = RecursiveTreeItem<StringRecursiveObject>(employeeInfoModels) { it.children }
        tableEmployee.root = root
        tableEmployee.isShowRoot = false
        tableEmployee.selectionModel.selectionMode = SelectionMode.SINGLE
    }

    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    override fun load(): UiElement {
        return doLoad("/fxml/helpui/CRUDSample.fxml")
    }

    fun onBtnDeleteClicked() {
        val selected = selected
        if (selected != null) {
            PromptDialogHelper("确定删除？", "警告：删除操作不可撤销且可能会影响相关单据。")
                .addCloseButton("取消", "CLOSE", null)
                .addButton("确定", "CHECK") { e ->
//                    blService.delete(selected)
                    PromptDialogHelper("删除成功", "")
                        .addCloseButton("好", "CHECK", EventHandler { e2 ->
                            Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                            updateItems()
                        })
                        .createAndShow()
                }
                .createAndShow()
        }
    }

    fun onBtnModifyClicked() {
        val selected = selected
        if (selected != null) {
            ModificationSampleController().show(selected) {
                this.updateItems()
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
            }
        }
    }

    fun onBtnAddClicked() {
//        EmployeeAddUiController().show(???({ this.updateItems() }))
    }
}
