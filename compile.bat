@echo off
cls
@echo on
g++ "main.cpp" "Window.cpp" "lib/GL_4_6_core.c" "Shader/Shader.cpp" "Shader/ShaderProgram.cpp" "Scene/Model.cpp" "Scene/Scene.cpp" "lib/stb.cpp" -lgdi32 -lopengl32 -Wno-write-strings -o JannledEngine
@echo off
