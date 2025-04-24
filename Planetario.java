import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Planetario3D extends Application {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    private double earthAngle = 0;
    private double moonAngle = 0;
    
    @Override
    public void start(Stage primaryStage) {
        // Crear la cámara
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateZ(-1000);
        
        // Crear el grupo raíz que contendrá todos los objetos 3D
        Group root = new Group();
        
        // Crear el sol (esfera amarilla)
        Sphere sun = new Sphere(100);
        sun.setTranslateX(0);
        sun.setTranslateY(0);
        sun.setTranslateZ(0);
        sun.setMaterial(new PhongMaterial(Color.YELLOW));
        
        // Crear la tierra (esfera azul)
        Sphere earth = new Sphere(40);
        earth.setTranslateX(300);
        earth.setMaterial(new PhongMaterial(Color.BLUE));
        
        // Crear la luna (esfera gris)
        Sphere moon = new Sphere(15);
        moon.setTranslateX(350);
        moon.setMaterial(new PhongMaterial(Color.GRAY));
        
        // Añadir todos los objetos al grupo raíz
        root.getChildren().addAll(sun, earth, moon);
        
        // Configurar la escena
        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        
        // Configurar el escenario
        primaryStage.setTitle("Planetario 3D");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Animación para rotar los planetas
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Rotar la tierra alrededor del sol
                earthAngle += 0.5;
                earth.setTranslateX(300 * Math.cos(Math.toRadians(earthAngle)));
                earth.setTranslateZ(300 * Math.sin(Math.toRadians(earthAngle)));
                
                // Rotar la luna alrededor de la tierra
                moonAngle += 2;
                double moonOrbitX = earth.getTranslateX() + 100 * Math.cos(Math.toRadians(moonAngle));
                double moonOrbitZ = earth.getTranslateZ() + 100 * Math.sin(Math.toRadians(moonAngle));
                moon.setTranslateX(moonOrbitX);
                moon.setTranslateZ(moonOrbitZ);
            }
        }.start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}