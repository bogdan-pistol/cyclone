CYCLONE
====================

    Cyclone project is a maven project that is composed of 2 modules:
    
    1. Cyclone server (cyclone-server)
    2. Cyclone UI (cyclone-ui)
    
    
## 1. CYCLONE SERVER
    Cyclone server is a **SPRINGBOOT** project that exposes an API for:
        - reading properties
        - edit properties 
        - add properties
        - import properties from files.
    Also the cyclone-server module has a plugin that imports the build of cyclone-ui
    into its own resources and it packs also the UI into the same image as the server.
    
        
        
## 2. CYCLONE UI
    Cyclone UI is an angular project, single page application.
    Also it is seated inside the a maven module.
    The root of the angular project is inside the module cyclone-ui in src/main/web.
    The maven module cyclone-ui has a plugin that also builds the angular project 
    when running mvn clean build/install/package in the root of the parent project.
    
## 3. SWAGGER UI
     To access swagger to see the server API please access    
     http://[host]:[server.port]/swagger-ui.html
     
     
#### BUILD & DELIVERY
    To build the entire project run **mvn clean package** in the root of the project.
    To build the image run docker build with the Dockerfile that is in cyclone-server.
    
    To run the entire build & delivery pipeline use the Jenkinsfile from the root of the
    project.
    