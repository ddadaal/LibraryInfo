package libraryinfo.presentation.helpui.validator

import com.jfoenix.controls.JFXTextField
import com.jfoenix.validation.DoubleValidator
import com.jfoenix.validation.NumberValidator
import com.jfoenix.validation.RequiredFieldValidator
import com.jfoenix.validation.base.ValidatorBase

object ValidatorHelper {
    private const val defaultRequiredPrompt = "请输入信息"
    private const val defaultNumberPrompt = "请输入整数"
    private const val defaultDoublePrompt = "请输入整数"

    fun addValidator(validateClass: Class<out ValidatorBase>, tf: JFXTextField, message: String?) {
        try {
            val base = validateClass.newInstance()
            if (message != null) {
                base.message = message
            }
            tf.validators.add(base)
            tf.focusedProperty().addListener { observable, oldValue, newValue ->
                if (!newValue) {
                    tf.validate()
                }
            }
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    fun addDefaultRequiredValidator(textField: JFXTextField) {
        addValidator(RequiredFieldValidator::class.java, textField, defaultRequiredPrompt)
    }

    fun addDefaultNumberValidator(textField: JFXTextField) {
        addValidator(NumberValidator::class.java, textField, defaultNumberPrompt)
    }

    fun addDefaultDoubleValidator(textField: JFXTextField) {
        addValidator(DoubleValidator::class.java, textField, defaultDoublePrompt)
    }
}
