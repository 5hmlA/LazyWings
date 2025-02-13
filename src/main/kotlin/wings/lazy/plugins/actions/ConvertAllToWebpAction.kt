package wings.lazy.plugins.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import wings.lazy.plugins.wizard.AndroidScopeProcessor
import wings.lazy.plugins.wizard.CheckerImpl
import wings.lazy.plugins.wizard.Environment

class ConvertAllToWebpAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = e.getData(CommonDataKeys.PSI_ELEMENT)
        if (psiElement is PsiDirectory) {
            psiElement.accept(object : PsiElementVisitor() {
                override fun visitFile(file: PsiFile) {
                    super.visitFile(file)
                    println(file.name)
                }
            })
            visitFile(psiElement).forEach {
                println(it.name)
            }
        }
//        ConvertToWebpAction
//        ExportToFileUtil
//        ExportProjectZip

        WriteCommandAction.runWriteCommandAction(e.project) {
            AndroidScopeProcessor(Environment(e.project!!, psiElement as PsiDirectory)).performRefactoring(
                e.getData(
                    CommonDataKeys.PSI_ELEMENT
                ) as PsiElement
            )
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        //文件夹 同时不是 代码范围
        e.presentation.isEnabledAndVisible = e.getData(CommonDataKeys.PSI_ELEMENT)
            ?.let { it is PsiDirectory && !CheckerImpl.Companion.instance.isCodeScope(e.dataContext) } ?: false
    }

    fun visitFile(psiDirectory: PsiDirectory):List<PsiFile> {
        val mutableListOf = mutableListOf<PsiFile>()
        if (psiDirectory.children.isEmpty()) {
            return mutableListOf
        }
        for (child in psiDirectory.children) {
            if (child is PsiDirectory) {
                mutableListOf.addAll(visitFile(child))
            }else if (child is PsiFile) {
                mutableListOf.add(child)
            } else {
                println(child)
            }
        }
        return mutableListOf
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}