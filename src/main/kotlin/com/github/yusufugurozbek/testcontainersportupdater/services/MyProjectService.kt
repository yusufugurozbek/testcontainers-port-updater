package com.github.yusufugurozbek.testcontainersportupdater.services

import com.github.yusufugurozbek.testcontainersportupdater.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
