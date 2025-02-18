package wings.lazy.plugins

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor


fun AnActionEvent.project() = getData(PlatformDataKeys.PROJECT)

fun AnActionEvent.caret() = getData(PlatformDataKeys.CARET)

fun AnActionEvent.editor() = getData(PlatformDataKeys.EDITOR_EVEN_IF_INACTIVE)

fun AnActionEvent.psiElement() = getData(PlatformDataKeys.PSI_ELEMENT)

fun AnActionEvent.psiFile() = getData(PlatformDataKeys.PSI_FILE)

fun AnActionEvent.virtualFile() = getData(PlatformDataKeys.VIRTUAL_FILE)

fun AnActionEvent.language() = getData(PlatformDataKeys.LANGUAGE)

fun Editor?.edit(edit: Document.() -> Unit) {
    if (this != null && document.isWritable) {
        document.edit()
    } else {
        Messages.showWarningDialog("请打开可编辑文件修改代码", "文件不可编辑")
    }
}

fun Project?.execute(name: String, groupId: String, runnable: Runnable) {
    CommandProcessor.getInstance().executeCommand(this, {
        ApplicationManager.getApplication().runWriteAction {
            runnable.run()
        }
    }, name, groupId)
}

fun clipboardString(): String? {
    try {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val contents = clipboard.getContents(null)
        // 检查剪切板是否包含文本数据
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            // 从剪切板获取字符串
            val text = contents.getTransferData(DataFlavor.stringFlavor) as String?
            return text
        } else {
            println("剪切板中没有文本内容")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}