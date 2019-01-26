package libraryinfo.presentation.helpui

import com.jfoenix.controls.JFXDialog
import java.util.*

class DialogStack {
    private val dialogStack = Stack<JFXDialog>()

    val top: JFXDialog?
        get() = if (dialogStack.empty()) null else dialogStack.peek()

    fun push(dialog: JFXDialog): DialogStack {
        dialogStack.push(dialog)
        return this
    }

    fun pushAndShow(dialog: JFXDialog): DialogStack {
        //        if (!dialogStack.empty()) {
        //            dialogStack.peek().close();
        //        }
        dialogStack.push(dialog)
        dialog.show()
        return this
    }

    fun closeCurrentAndPopAndShowNext(): DialogStack {
        if (!dialogStack.empty()) {
            dialogStack.pop().close()
        }
        //        if (!dialogStack.empty()) {
        //            dialogStack.peek().show();
        //        }
        return this
    }

    fun closeTop(): DialogStack {
        if (!dialogStack.empty()) {
            dialogStack.peek().close()
        }
        return this
    }

    fun pop(): DialogStack {
        if (!dialogStack.empty()) {
            dialogStack.pop()
        }
        return this
    }

}
