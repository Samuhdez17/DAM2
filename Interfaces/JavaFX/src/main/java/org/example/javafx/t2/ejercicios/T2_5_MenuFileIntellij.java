package org.example.javafx.t2.ejercicios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T2_5_MenuFileIntellij extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MenuBar menuBar = new MenuBar();
        // Menu File
        Menu menuFile = new Menu("File");
        Menu itemNew = new Menu("New");
        MenuItem itemOpen = new MenuItem("Open...");
        Menu itemRecentProjects = new Menu("Recent Projects");
        MenuItem itemCloseProject = new MenuItem("Close Project");
        MenuItem itemRemoteDevelopment = new MenuItem("Remote Development...");
        MenuItem itemSettings = new MenuItem("Settings...");
        MenuItem itemProjectStructure = new MenuItem("Project Structure...");
        Menu itemFileProperties = new Menu("File Properties");
        Menu itemLocalHistory = new Menu("Local History");
        MenuItem itemSaveAll = new MenuItem("Save All");
        MenuItem itemReloadAllFromDisk = new MenuItem("Reload All from Disk");
        MenuItem itemRepairIDE = new MenuItem("Repair IDE");
        MenuItem itemInvalidateCaches = new MenuItem("Invalidate Caches...");
        Menu itemManageIDESettings = new Menu("Manage IDE Settings");
        Menu itemNewProjectsSetup = new Menu("New Projects Setup");
        MenuItem itemSaveFileAsTemplate = new MenuItem("Save File as Template...");
        Menu itemExport = new Menu("Export");
        MenuItem itemPrint = new MenuItem("Print...");
        MenuItem itemPowerSaveMode = new MenuItem("Power Save Mode");
        MenuItem itemExit = new MenuItem("Exit");

        // Items Menu > File > New
        MenuItem itemProject = new MenuItem("Project...");
        MenuItem itemProjectFromExistingResources = new MenuItem("Project from Existing Resources...");
        MenuItem itemProjectFromVersionControl = new MenuItem("Project from Version Control...");
        MenuItem itemModule = new MenuItem("Module...");
        MenuItem itemModuleFromExistingResources = new MenuItem("Module from Existing Resources...");
        MenuItem itemJavaClass = new MenuItem("Java Class");
        MenuItem itemKotlinClass_File = new MenuItem("Kotlin Class/File");
        MenuItem itemFile = new MenuItem("File");
        MenuItem itemPackage = new MenuItem("Package");
        MenuItem itemFXMLFile = new MenuItem("FXML File");
        MenuItem itemJavaFXApplication = new MenuItem("JavaFX Application");
        MenuItem itemPackage_info_java = new MenuItem("package-info.java");
        MenuItem itemModule_info_java = new MenuItem("module-info.java");
        MenuItem itemKotlinNotebook = new MenuItem("Kotlin Notebook");
        MenuItem itemResourceBundle = new MenuItem("Resource Bundle");
        itemModule_info_java.setDisable(true);

        // Items Menu > File > Recent Projects
        MenuItem proy1 = new MenuItem("Proyecto 1");
        MenuItem proy2 = new MenuItem("Proyecto 2");
        MenuItem proy3 = new MenuItem("Proyecto 3");
        MenuItem ManageProjects = new MenuItem("Manage Projects...");

        // Items Menu > File > File Properties
        MenuItem itemFileEncoding = new MenuItem("File Encoding");
        MenuItem itemRemoveBOM = new MenuItem("Remove BOM");
        MenuItem itemAddBOM = new MenuItem("Add BOM");
        MenuItem itemAssociateWithFileType = new MenuItem("Associate with File Type...");
        MenuItem itemMakeFileRead_Only = new MenuItem("Make file read-only");
        Menu itemLineSeparators = new Menu("Line Separators");
        itemRemoveBOM.setDisable(true);

        // Items Menu > File > File Properties > Line Separators
        MenuItem itemCRLF = new MenuItem("CRLF - Windows (\\r\\n)");
        MenuItem itemLF = new MenuItem("LF - Unix and macOS (\\n)");
        MenuItem itemCR = new MenuItem("Classic Mac OS (\\r)");

        // Items Menu > File > Local History
        MenuItem itemShowHistory = new MenuItem("Show History...");
        MenuItem itemShowHistoryForSelection = new MenuItem("Show History for Selection...");
        MenuItem itemShowProjectHistory = new MenuItem("Show Project History...");
        MenuItem itemRecentChanges = new MenuItem("Recent Changes");
        MenuItem itemPutLabel = new MenuItem("Put Label...");

        // Items Menu > File > Manage IDE Settings
        MenuItem itemImportSettings = new MenuItem("Import Settings...");
        MenuItem itemExportSettings = new MenuItem("Export Settings...");
        MenuItem itemRestoreDefaultsSettings = new MenuItem("Restore Defaults Settings...");
        MenuItem itemBackupAndSync = new MenuItem("Backup and Sync...");

        // Items Menu > File > New > New Projects Setup
        MenuItem itemSettingsForNewProjects = new  MenuItem("Settings for Mew Projects...");
        MenuItem itemRunConfigurationTemplates = new  MenuItem("Run Configuration Templates...");
        MenuItem itemStructure = new  MenuItem("Structure...");
        MenuItem itemSaveProjectAsTemplate = new  MenuItem("Save Project as Template...");
        MenuItem itemManageProjectsTemplates = new  MenuItem("Manage Projects Templates...");

        // Items Menu > File > New > Export
        MenuItem itemFilesOrSelectionToHTML = new MenuItem("Files or Selection To HTML...");
        MenuItem itemProjectToEclipse = new MenuItem("Project to Eclipse...");

        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");

        // Agregaciones a menuBar
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        // Agregaciones a menuFile
        menuFile.getItems().addAll(itemNew, itemOpen, itemRecentProjects, itemCloseProject, itemRemoteDevelopment, itemSettings, itemProjectStructure, itemFileProperties, itemLocalHistory, itemSaveAll, itemReloadAllFromDisk, itemRepairIDE, itemInvalidateCaches, itemManageIDESettings, itemNewProjectsSetup, itemSaveFileAsTemplate, itemExport, itemPrint, itemPowerSaveMode, itemExit);
        // Agregaciones a itemNew
        itemNew.getItems().addAll(itemProject, itemProjectFromExistingResources, itemProjectFromVersionControl, itemModule, itemModuleFromExistingResources, itemJavaClass, itemKotlinClass_File, itemFile, itemPackage, itemFXMLFile, itemJavaFXApplication, itemPackage_info_java, itemModule_info_java, itemKotlinNotebook, itemResourceBundle);
        // Agregaciones a itemRecentProjects
        itemRecentProjects.getItems().addAll(proy1, proy2, proy3, ManageProjects);
        // Agregaciones a itemFileProperties
        itemFileProperties.getItems().addAll(itemFileEncoding, itemRemoveBOM, itemAddBOM, itemAssociateWithFileType, itemMakeFileRead_Only, itemLineSeparators);
        // Agregaciones a itemLineSeparators
        itemLineSeparators.getItems().addAll(itemCRLF, itemLF, itemCR);
        // Agregaciones a itemLocalHistory
        itemLocalHistory.getItems().addAll(itemShowHistory, itemShowHistoryForSelection, itemShowProjectHistory, itemRecentChanges, itemPutLabel);
        // Agregaciones a itemManageIDESettings
        itemManageIDESettings.getItems().addAll(itemImportSettings, itemExportSettings, itemRestoreDefaultsSettings, itemBackupAndSync);
        // Agregaciones a itemNewProjectsSetup
        itemNewProjectsSetup.getItems().addAll(itemSettingsForNewProjects, itemRunConfigurationTemplates, itemStructure, itemSaveProjectAsTemplate, itemManageProjectsTemplates);
        // Agregaciones a itemExport
        itemExport.getItems().addAll(itemFilesOrSelectionToHTML, itemProjectToEclipse);

        // Separadores Menu File
        SeparatorMenuItem sep_file_1 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_2 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_3 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_4 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_5 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_6 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_7 = new SeparatorMenuItem();
        SeparatorMenuItem sep_file_8 = new SeparatorMenuItem();

        menuFile.getItems().add( 4, sep_file_1);
        menuFile.getItems().add( 6, sep_file_2);
        menuFile.getItems().add(10, sep_file_3);
        menuFile.getItems().add(12, sep_file_4);
        menuFile.getItems().add(17, sep_file_5);
        menuFile.getItems().add(21, sep_file_6);
        menuFile.getItems().add(24, sep_file_7);
        menuFile.getItems().add(26, sep_file_8);

        // Separadores Menu > File > New
        SeparatorMenuItem sep_new_1 = new SeparatorMenuItem();
        SeparatorMenuItem sep_new_2 = new SeparatorMenuItem();
        SeparatorMenuItem sep_new_3 = new SeparatorMenuItem();

        itemNew.getItems().add(3, sep_new_1);
        itemNew.getItems().add(6, sep_new_2);
        itemNew.getItems().add(15, sep_new_3);

        // Separador Menu > File > Recent Projects
        SeparatorMenuItem sep_recent = new SeparatorMenuItem();

        itemRecentProjects.getItems().add(3, sep_recent);

        // Separador Menu > File > Local History
        SeparatorMenuItem sep_localHis = new SeparatorMenuItem();

        itemLocalHistory.getItems().add(2, sep_localHis);

        // Separador Menu > File > Manage IDE Settings
        SeparatorMenuItem sep_manage = new SeparatorMenuItem();

        itemManageIDESettings.getItems().add(2, sep_manage);

        // Agregación de atajos
        itemSettings.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+S"));
        itemProjectStructure.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+Shift+S"));
        itemSaveAll.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        itemPrint.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        // Agregación y asignación de imágenes
        String ruta = "file:src/main/java/org/example/javafx/t2/ejercicios/images/";
        Image carpetaOpen = new Image(ruta + "carpeta.png");
        Image carpetaProjectStructure = new Image(ruta + "carpeta_project_structure.png");
        Image guardar = new Image(ruta + "guardar.png");
        Image imprimir = new Image(ruta + "imprimir.png");
        Image recarga = new Image(ruta + "recarga.png");
        Image ajustes = new Image(ruta + "tuerca.png");
        Image javaClass = new Image(ruta + "java_class.png");
        Image kotlinClass = new Image(ruta + "kotlin_class.png");
        Image file = new Image(ruta + "file.png");
        Image carpeta = new Image(ruta + "package.png");
        Image fxmlFile = new Image(ruta + "fxml.png");
        Image jfxApp = new Image(ruta + "java_class.png");
        Image packageInfo = new Image(ruta + "package-info.png");
        Image moduleInfo = new Image(ruta + "module-info.png");
        Image kotlinNotebook = new Image(ruta + "kotlin_notebook.png");
        Image resourceBundle = new Image(ruta + "tuerca.png");

        asignarImagen(itemOpen, itemSettings, itemProjectStructure, itemSaveAll, itemReloadAllFromDisk, itemPrint, itemJavaClass, itemKotlinClass_File, carpetaOpen, carpetaProjectStructure, guardar, imprimir, recarga, ajustes, javaClass, kotlinClass);
        asignarImagen(itemFile, itemPackage, itemFXMLFile, itemJavaFXApplication, itemPackage_info_java, itemModule_info_java, itemKotlinNotebook, itemResourceBundle, file, fxmlFile, jfxApp, moduleInfo, packageInfo, carpeta, kotlinNotebook, resourceBundle);

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);

        stage.setScene(new Scene(bp));
        stage.setTitle("T2_5_MenuFileIntellij");
        stage.show();
    }

    private void asignarImagen(MenuItem itemOpen, MenuItem itemSettings, MenuItem itemProjectStructure, MenuItem itemSaveAll, MenuItem itemReloadAllFromDisk, MenuItem itemPrint, MenuItem itemJavaClass, MenuItem itemKotlinClass_File, Image carpetaOpen, Image carpetaProjectStructure, Image guardar, Image imprimir, Image recarga, Image ajustes, Image javaClass, Image kotlinClass) {
        itemOpen.setGraphic(new ImageView(carpetaOpen));
        itemSettings.setGraphic(new ImageView(ajustes));
        itemProjectStructure.setGraphic(new ImageView(carpetaProjectStructure));
        itemSaveAll.setGraphic(new ImageView(guardar));
        itemReloadAllFromDisk.setGraphic(new ImageView(recarga));
        itemPrint.setGraphic(new ImageView(imprimir));
        itemJavaClass.setGraphic(new ImageView(javaClass));
        itemKotlinClass_File.setGraphic(new ImageView(kotlinClass));
    }
}
