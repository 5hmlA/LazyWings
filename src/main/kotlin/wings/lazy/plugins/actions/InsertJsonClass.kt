package wings.lazy.plugins.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Document
import wings.lazy.plugins.*


class InsertJsonClass : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        val project = event.project()
        val caret = event.caret()
        val editor = event.editor()
        editor.edit {
            project.execute("insert json class", "insert") {
                val offset = calculateOffset(caret, this)
                insertString(
                    offset,
                    clipboardString() ?: "新加入的内容"
                )
            }
        }
    }


    private fun calculateOffset(caret: Caret?, document: Document): Int {
        var offset: Int
        if (caret != null) {
            offset = caret.offset
            if (offset == 0) {
                offset = document.textLength
            }
            val lastPackageKeywordLineEndIndex = try {
                "^[\\s]*package\\s.+\n$".toRegex(RegexOption.MULTILINE).findAll(document.text).last().range.last
            } catch (e: Exception) {
                -1
            }
            val lastImportKeywordLineEndIndex = try {
                "^[\\s]*import\\s.+\n$".toRegex(RegexOption.MULTILINE).findAll(document.text).last().range.last
            } catch (e: Exception) {
                -1
            }
            if (offset < lastPackageKeywordLineEndIndex) {
                offset = lastPackageKeywordLineEndIndex + 1
            }
            if (offset < lastImportKeywordLineEndIndex) {
                offset = lastImportKeywordLineEndIndex + 1
            }

        } else {
            offset = document.textLength
        }

        return offset
    }
}