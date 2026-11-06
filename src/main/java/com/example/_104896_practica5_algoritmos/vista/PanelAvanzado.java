package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.modelo.TipoLista;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PanelAvanzado extends AnchorPane{
    private static final double RESX=1920.0;
    private static final double RESGAEL=1080.0;
    private Controlador controlador;
    private PantallaGaleria galeria;
    private TipoLista criterio;
    private ImageButton btnTodas;
    private ImageButton btnNivel;
    private ImageButton btnAtributo;
    private ImageButton btnAtaque;
    private ImageButton btnDefensa;
    private ImageButton btnBuscar;
    private ImageButton btnRestablecer;
    private ComboBox<String> comboNiveles;
    private ComboBox<String> comboAtributos;
    private CheckBox checkAscendente;
    private Label lbMensaje;

    public PanelAvanzado(Controlador controlador,PantallaGaleria galeria){
        this.controlador=controlador;
        this.galeria=galeria;
        setPickOnBounds(false);
        setStyle("-fx-background-color:rgba(10,15,40,0.85);-fx-border-color:#d4af37;-fx-border-width:2 0 2 0;");
        construirPantalla();
        elegirCriterio(TipoLista.GENERAL);
    }

    private void construirPantalla(){
        btnTodas=new ImageButton("/recursos/btnTodas.png","/recursos/btnTodasHover.png",240,135);
        btnTodas.setOnAction(e->elegirCriterio(TipoLista.GENERAL));
        btnNivel=new ImageButton("/recursos/btnPorNivel.png","/recursos/btnPorNivelHover.png",240,135);
        btnNivel.setOnAction(e->elegirCriterio(TipoLista.NIVEL));
        btnAtributo=new ImageButton("/recursos/btnPorAtributo.png","/recursos/btnPorAtributoHover.png",240,135);
        btnAtributo.setOnAction(e->elegirCriterio(TipoLista.ATRIBUTO));
        btnAtaque=new ImageButton("/recursos/btnPorAtaque.png","/recursos/btnPorAtaqueHover.png",240,135);
        btnAtaque.setOnAction(e->elegirCriterio(TipoLista.ATAQUE));
        btnDefensa=new ImageButton("/recursos/btnPorDefensa.png","/recursos/btnPorDefensaHover.png",240,135);
        btnDefensa.setOnAction(e->elegirCriterio(TipoLista.DEFENSA));

        comboNiveles=new ComboBox<>();
        comboNiveles.getItems().addAll(controlador.getNiveles());
        comboNiveles.setValue(comboNiveles.getItems().get(0));
        comboNiveles.setPrefWidth(180);
        comboNiveles.setStyle("-fx-border-color:#d4af37;-fx-font-size:16px;");

        comboAtributos=new ComboBox<>();
        comboAtributos.getItems().addAll(controlador.getAtributos());
        comboAtributos.setValue(comboAtributos.getItems().get(0));
        comboAtributos.setPrefWidth(180);
        comboAtributos.setStyle("-fx-border-color:#d4af37;-fx-font-size:16px;");

        checkAscendente=new CheckBox("Menor a mayor");
        checkAscendente.setSelected(true);
        checkAscendente.setStyle("-fx-text-fill:white;-fx-font-size:18px;");

        lbMensaje=new Label();
        lbMensaje.setStyle("-fx-text-fill:#ff6666;-fx-font-size:15px;");
        lbMensaje.setWrapText(true);
        lbMensaje.setMaxWidth(260);
        lbMensaje.setVisible(false);

        btnBuscar=new ImageButton("/recursos/btnBuscar.png","/recursos/btnBuscar.png",120,120);
        btnBuscar.setOnAction(e->buscar());
        btnRestablecer=new ImageButton("/recursos/btnRestablecer.png","/recursos/btnRestablecer.png",240,100);
        btnRestablecer.setOnAction(e->restablecer());

        getChildren().addAll(btnTodas,btnNivel,btnAtributo,btnAtaque,btnDefensa,comboNiveles,comboAtributos,checkAscendente,lbMensaje,btnBuscar,btnRestablecer);
        posicionarEnPane(btnTodas,40/RESX,-20/RESGAEL);
        posicionarEnPane(btnNivel,340/RESX,-20/RESGAEL);
        posicionarEnPane(btnAtributo,640/RESX,-20/RESGAEL);
        posicionarEnPane(btnAtaque,940/RESX,-20/RESGAEL);
        posicionarEnPane(btnDefensa,1240/RESX,-20/RESGAEL);
        posicionarEnPane(comboNiveles,375/RESX,700/RESGAEL);
        posicionarEnPane(comboAtributos,675/RESX,700/RESGAEL);
        posicionarEnPane(checkAscendente,1125/RESX,700/RESGAEL);
        posicionarEnPane(lbMensaje,1330/RESX,100/RESGAEL);
        posicionarEnPane(btnBuscar,1680/RESX,40/RESGAEL);
        posicionarEnPane(btnRestablecer,1600/RESX,500/RESGAEL);
    }

    private void elegirCriterio(TipoLista tipo){
        criterio=tipo;
        lbMensaje.setVisible(false);
        comboNiveles.setVisible(tipo==TipoLista.NIVEL);
        comboAtributos.setVisible(tipo==TipoLista.ATRIBUTO);
        checkAscendente.setVisible(tipo==TipoLista.ATAQUE||tipo==TipoLista.DEFENSA);

        btnTodas.setEffect(null);
        btnNivel.setEffect(null);
        btnAtributo.setEffect(null);
        btnAtaque.setEffect(null);
        btnDefensa.setEffect(null);
        DropShadow brillo=new DropShadow(20,Color.GOLD);
        switch (tipo){
            case NIVEL:
                btnNivel.setEffect(brillo);
                break;
            case ATRIBUTO:
                btnAtributo.setEffect(brillo);
                break;
            case ATAQUE:
                btnAtaque.setEffect(brillo);
                break;
            case DEFENSA:
                btnDefensa.setEffect(brillo);
                break;
            default:
                btnTodas.setEffect(brillo);
        }
    }

    private void buscar(){
        boolean hayCartas;
        switch (criterio){
            case NIVEL:
                hayCartas=controlador.verPorNivel(comboNiveles.getValue());
                break;
            case ATRIBUTO:
                hayCartas=controlador.verPorAtributo(comboAtributos.getValue());
                break;
            case ATAQUE:
                hayCartas=controlador.verPorAtaque(checkAscendente.isSelected());
                break;
            case DEFENSA:
                hayCartas=controlador.verPorDefensa(checkAscendente.isSelected());
                break;
            default:
                hayCartas=controlador.verTodas();
        }
        if (hayCartas){
            lbMensaje.setVisible(false);
            galeria.actualizarPantalla();
        } else {
            lbMensaje.setText("No hay cartas con ese criterio");
            lbMensaje.setVisible(true);
        }
    }

    private void restablecer(){
        comboNiveles.setValue(comboNiveles.getItems().get(0));
        comboAtributos.setValue(comboAtributos.getItems().get(0));
        checkAscendente.setSelected(true);
        elegirCriterio(TipoLista.GENERAL);
        controlador.verTodas();
        galeria.actualizarPantalla();
    }

    public void marcarTodas(){
        elegirCriterio(TipoLista.GENERAL);
    }

    private void posicionarEnPane(Node nodo,double porcentajeX,double porcentajeY){
        nodo.translateXProperty().unbind();
        nodo.translateYProperty().unbind();
        nodo.translateXProperty().bind(widthProperty().multiply(porcentajeX));
        nodo.translateYProperty().bind(heightProperty().multiply(porcentajeY));
    }
}