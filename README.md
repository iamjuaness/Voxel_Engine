# Voxel Engine - JuanCraft

 <p align="left">
   <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
 </p>

 <h1 align="left">
  🚧 Proyecto en construcción 🚧</h1>
   <p></p>

JuanCraft is a 3D game built using the Lightweight Java Game Library (LWJGL). The project demonstrates the use of OpenGL for rendering graphics and provides a basic structure for developing a game.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#%EF%B8%8F-installation)
- [Usage](#-usage)
- [Contributing](#-contributing)
- [License](#-license)
- [Acknowledgments](#-acknowledgments)
- [Author](#-author)

## Features
- Fullscreen display with configurable resolution.
- Basic 3D rendering of models using Vertex Array Objects (VAOs) and Vertex Buffer Objects (VBOs).
- Mouse input handling for an immersive experience.
- Keyboard input handling for user interaction.
- A simple game loop for continuous rendering.

## Technologies Used
- **Java**: The programming language used for the project.
- **LWJGL (Lightweight Java Game Library)**: A library that provides access to OpenGL for rendering graphics and other features.
- **OpenGL**: The graphics rendering API used to display 3D graphics.

##  Getting Started

### Prerequisites
To run this project, you need:
- **Java Development Kit (JDK)**: Version 8 or later installed on your machine.
- **LWJGL library**: The Lightweight Java Game Library for OpenGL rendering.

### ⬇️ Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/iamjuaness/Voxel_Engine.git
   cd Voxel_Engine
   
2. **Download LWJGL**:
   
   - Visit the [LWJGL official website](https://www.lwjgl.org/) and download the latest version of the library.
     
   - Follow the instructions provided on the website to include LWJGL in your project. Make sure to download the necessary .jar files and native libraries suitable for your operating system (Windows, Linux, or macOS).
     
3. **Set up the project in your IDE**:
   
    - Open your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
      
    - Import the project directory you just cloned as a new project.
      
    - Add the LWJGL JAR files to your project's build path:
      
      - In Eclipse:
        
         - Right-click on the project in the Project Explorer.
           
         - Select Build Path > Configure Build Path...
           
         - Under the Libraries tab, click Add External JARs... and select the LWJGL .jar files you downloaded.
           
      - In IntelliJ IDEA:
     
        - Go to File > Project Structure.
          
        - Click on Modules, then on the Dependencies tab.
          
        - Click the + icon, select JARs or directories, and choose the LWJGL .jar files.

  4. **Configure Native Libraries**:
     
      - LWJGL requires native libraries specific to your operating system. Ensure these libraries are linked correctly:
        
        - In Eclipse, after adding the .jar files, you may need to configure the native library location:
          
          - Right-click on the .jar file in the Package Explorer.
            
          - Select Properties, then go to the Java Build Path section.
            
          - Under the Native library location, click Edit... and point it to the folder containing the native files.
            
        - In IntelliJ IDEA, set the native library path:
          
          - Go to File > Project Structure > Libraries.
            
          - For each LWJGL library, select it and specify the path to the native libraries in the Native Library Location.
         
  5. **Run the project**:
     
      - Locate the MainGameLoop class in the Voxel_Engine/JuanCraft package.
        
      - Right-click on the file and select Run 'MainGameLoop.main()' to start the game.
        
      - Alternatively, you can run it from the command line with:
        
        ```bash
        java -cp "path/to/lwjgl.jar;path/to/other/libraries;." Voxel_Engine.JuanCraft.MainGameLoop
    
      - Make sure to replace path/to/lwjgl.jar and path/to/other/libraries with the actual paths to your LWJGL .jar files.
  6. **Enjoy the game**:

      - Once the game starts, use the following controls:
        - Press 'E' to toggle mouse capturing.
        - Press 'ESCAPE' to exit the game.
       

### 📄 Summary of the Steps:
1. **Clone the repository** to get the project files.
   
2. **Download LWJGL** to obtain the necessary libraries for rendering.
   
3. **Set up the project in your IDE** to include the LWJGL libraries.
   
4. **Configure native libraries** to ensure LWJGL works correctly on your system.
   
5. **Run the project** to start the game.
    
6. **Enjoy the game** using the specified controls.

Feel free to adjust any specific details based on your project's requirements or any additional setup steps you may have! If you need more sections or further help, just let me know!


 ## 🎮 Usage
  - Controls:
          
    - 'E': Toggle mouse capturing.
            
    - 'ESCAPE': Exit the game.

## ✍🏻 Contributing
If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
   
2. Create a new branch (git checkout -b feature/YourFeature).
   
3. Make your changes and commit them (git commit -m "Add some feature").
   
4. Push to the branch (git push origin feature/YourFeature).

5. Open a Pull Request.

## 📃 License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/iamjuaness/Voxel_Engine/blob/master/LICENSE) file for details.

## 👏🏻 Acknowledgments
- Special thanks to the LWJGL community for their resources and support.
  
- Special thanks to youtube channel user [@ReonFourie](https://www.youtube.com/@ReonFourie), for his series of videos on how to generate a Voxel Engine.

## 💻 Author

| [<img src="https://avatars.githubusercontent.com/u/104481229?v=4" width=115><br><sub>Juan E. Cardona</sub>](https://github.com/iamjuaness)
| :---: |
