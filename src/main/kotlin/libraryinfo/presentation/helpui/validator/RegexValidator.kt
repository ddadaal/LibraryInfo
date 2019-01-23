package libraryinfo.presentation.helpui.validator

import com.jfoenix.validation.base.ValidatorBase
import javafx.scene.control.TextInputControl

open class RegexValidator(private val regex: String) : ValidatorBase() {
    /**
     * will evaluate the validation condition once calling validate method
     */
    override fun eval() {
        val control = srcControl.get()
        if (control is TextInputControl) {
            val text = control.text
            hasErrors.set(!text.matches(regex.toRegex()))
        }
    }
}
