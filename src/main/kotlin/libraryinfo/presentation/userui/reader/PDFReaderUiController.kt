package libraryinfo.presentation.userui.reader

import javafx.event.ActionEvent
import javafx.scene.control.Label
import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import java.awt.Desktop
import java.net.URI

class PDFReaderUiController: UiController {

    lateinit var labelPath: Label

    lateinit var pdf: Pdf

    override fun load(): UiElement {
        return doLoad("/fxml/userui/reader/PdfReader.fxml")
    }

    fun fillContent(pdf: Pdf) {
        this.pdf = pdf
        labelPath.text = pdf.path
    }

    fun onBtnOpenClicked(actionEvent: ActionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(URI(pdf.path))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}