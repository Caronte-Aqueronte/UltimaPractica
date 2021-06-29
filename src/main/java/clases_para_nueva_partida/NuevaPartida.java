package clases_para_nueva_partida;

import clases_para_tablero.Tablero;
import interfaces_gui.MenuPrincipal;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jugador.Jugador;
import principal.Principal;

public class NuevaPartida extends javax.swing.JFrame {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int espacioX;
    private int espacioY;
    private JButton[][] botones;
    //imagenes
    private final ImageIcon piezaRojaImagen = new ImageIcon(getClass().getResource("/imagenes/solorojo.png"));
    private final ImageIcon piezaAmarillaImagen = new ImageIcon(getClass().getResource("/imagenes/soloamarillo.png"));
    private final ImageIcon ambasPiezasImagen = new ImageIcon(getClass().getResource("/imagenes/dospiezas.png"));

    private final ImageIcon avanzaImagen = new ImageIcon(getClass().getResource("/imagenes/avanza.png"));
    private final ImageIcon bajadaImagen = new ImageIcon(getClass().getResource("/imagenes/bajada.png"));
    private final ImageIcon caritaFelizImagen = new ImageIcon(getClass().getResource("/imagenes/caritafeliz.png"));
    private final ImageIcon dadosImagen = new ImageIcon(getClass().getResource("/imagenes/dospiezas.png"));
    private final ImageIcon pierdeTurnoImagen = new ImageIcon(getClass().getResource("/imagenes/pierdeturno.png"));
    private final ImageIcon retrocedeImagen = new ImageIcon(getClass().getResource("/imagenes/retrocede.png"));
    private final ImageIcon subidaImagen = new ImageIcon(getClass().getResource("/imagenes/subida.png"));
    //
    //Imgenes reescaladas
    private ImageIcon avanza = new ImageIcon(avanzaImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon bajada = new ImageIcon(bajadaImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon caritaFeliz = new ImageIcon(caritaFelizImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon dados = new ImageIcon(dadosImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon pierdeTurno = new ImageIcon(pierdeTurnoImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon retrocede = new ImageIcon(retrocedeImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon subida = new ImageIcon(subidaImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon roja = new ImageIcon(piezaRojaImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon amarilla = new ImageIcon(piezaAmarillaImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    private ImageIcon ambas = new ImageIcon(ambasPiezasImagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    //
    private boolean banderaPieza = false;
    private HiloNumeroAleatorio hilo;
    private int turno = 1;
    private int jugadorSinTurno = 0;

    /**
     * Constructor
     *
     * @param tablero
     * @param jugadores
     */
    public NuevaPartida(Tablero tablero, ArrayList<Jugador> jugadores) {
        initComponents();
        this.tablero = tablero;
        this.jugadores = jugadores;
        botones = new JButton[tablero.getX()][tablero.getY()];
        generarTablero();
        saberNombreDelJugadorDeTurno(turno);
    }

    //este metodo crea el tablero tomano en cuenta el tamanyo de la matriz de casilla creadas en tablero
    public void generarTablero() {
        try {
            //este contador nos sirve para darle nombre al boton
            int contador = 1;
            ajustarBotonesAlPanel(tablero.getX(), tablero.getY(), contenedor);
            //tamaño de gridLayout del panel
            contenedor.setLayout(new GridLayout(tablero.getX(), tablero.getY()));
            for (int x = 0; x < tablero.getMiTablero().length; x++) {
                for (int y = 0; y < tablero.getMiTablero()[x].length; y++) {
                    //CREAMOS EL BOTON COMO LO QUEREMOS
                    JButton boton = new JButton();
                    boton.setFont(new java.awt.Font("Verdana", 0, 16));
                    boton.setToolTipText(x + "," + y);
                    boton.setText(String.valueOf(contador));
                    boton.setSize(espacioX, espacioY);
                    boton.setFocusable(false);
                    //
                    if (contador == 1) {
                        boton.setIcon(ambas);
                    } else {
                        switch (tablero.getMiTablero()[x][y].getComportamiento()) {

                            case "pierdeturno":
                                boton.setIcon(pierdeTurno);
                                break;
                            case "tiradados":
                                boton.setIcon(dados);
                                break;
                            case "avanza":
                                boton.setIcon(avanza);
                                break;
                            case "retrocede":
                                boton.setIcon(retrocede);
                                break;
                            case "subida":
                                boton.setIcon(subida);
                                break;
                            case "bajada":
                                boton.setIcon(bajada);
                                break;
                            default:
                                boton.setIcon(caritaFeliz);
                                break;
                        }
                    }
                    contenedor.add(boton);
                    actualizarTablero(contenedor);
                    botones[x][y] = boton;
                    contador++;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void saberNombreDelJugadorDeTurno(int turno) {
        labelNombre.setText(jugadores.get(turno - 1).getNombre());
    }

    public void saberSiHayGanador() throws IOException {
        for (int x = 0; x < botones.length; x++) {
            for (int y = 0; y < botones[x].length; y++) {
                if (x == botones.length - 1 && y == botones[x].length - 1) {
                    if (botones[x][y].getIcon().toString().equals(roja.toString())) {
                        JOptionPane.showMessageDialog(null, "El ganador es " + jugadores.get(0).getNombre());
                        sobreEscribirJugadores(jugadores.get(0).getId(), jugadores.get(1).getId());
                        MenuPrincipal menu = new MenuPrincipal();
                        menu.setVisible(true);
                        this.dispose();
                    } else if (botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                        JOptionPane.showMessageDialog(null, "El ganador es " + jugadores.get(1).getNombre());
                        sobreEscribirJugadores(jugadores.get(1).getId(), jugadores.get(0).getId());
                        MenuPrincipal menu = new MenuPrincipal();
                        menu.setVisible(true);
                        this.dispose();
                    }
                }
            }
        }
    }

    public void sobreEscribirJugadores(int idGanador, int idPerdedor) throws IOException {
        for (Jugador item : Principal.jugadores.getJugadores()) {
            if (idGanador == item.getId()) {
                item.setPartidasJugadas(item.getPartidasJugadas() + 1);
                item.setPartidasGanadas(item.getPartidasGanadas() + 1);
            }
            if (idPerdedor == item.getId()) {
                item.setPartidasJugadas(item.getPartidasJugadas() + 1);
                item.setPartidasPerdidas(item.getPartidasPerdidas() + 1);
            }
        }
        Principal.jugadores.guardarDatosEnBinario();
    }

    // este metodo da el tamano de los botones para que se acoplen al panel
    public void ajustarBotonesAlPanel(int cuadriculaColumnas, int cuadriculaFilas, JPanel panel) {
        espacioX = panel.getX() / cuadriculaColumnas;
        espacioY = panel.getY() / cuadriculaFilas;
    }

    // este metodo actiliza el tablero cada que se agrega una nueva columna
    private void actualizarTablero(JPanel panel) {
        panel.validate();
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelDado1 = new javax.swing.JLabel();
        labelDado2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Vuelve a precionar ENTER para tirar los dados");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("Preciona ENTER par sacudor los dados");

        labelDado1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelDado1.setText("1");

        labelDado2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelDado2.setText("1");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Turno de");

        labelNombre.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(labelDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(labelDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && banderaPieza == false) {
            hilo = new HiloNumeroAleatorio("Hilo numero aleatorio", labelDado1, labelDado2);
            banderaPieza = true;
            hilo.start();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER && banderaPieza == true) {
            banderaPieza = false;
            hilo.detener();
            int suma = Integer.valueOf(labelDado1.getText()) + Integer.valueOf(labelDado2.getText());
            rastrearPieza(suma);

            try {

                saberSiHayGanador();
            } catch (IOException ex) {
                Logger.getLogger(NuevaPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * Este bloque de instrucciones avanza el turno para obtener el
             * nomrbe del jugador ademas reinicia el tuno cada que se vulva 3
             * para evitar excepciones
             */
            turno++;
            if (turno >= 3) {
                turno = 1;
            }
            if (jugadorSinTurno == 2) {
                jugadorSinTurno = 0;
                turno = 1;
            } else if (jugadorSinTurno == 1) {
                jugadorSinTurno = 0;
                turno = 2;
            }
            saberNombreDelJugadorDeTurno(turno);
        }
    }//GEN-LAST:event_formKeyPressed
    public void rastrearPieza(int suma) {
        int numeroDePieza = 0;
        int posX = 0;
        int posY = 0;
        principal:
        for (int x = 0; x < botones.length; x++) {
            for (int y = 0; y < botones[x].length; y++) {
                //con los iteradores del for este if ve si las piezas etan juntas
                if (botones[x][y].getIcon().toString().equals(ambas.toString())) {
                    numeroDePieza = Integer.valueOf(botones[x][y].getText()); //anotamos elnumero de casilla
                    posX = x; //guardamos la poscision x de la casilla
                    posY = y; //guardamos la posicion y de la casilla
                    break principal;
                } else {
                    //segun turno de quien sea, asi se buscara el color de la pieza 1 es pieza roja 2 es pieza amarilla
                    switch (turno) {
                        case 1:
                            if (botones[x][y].getIcon().toString().equals(roja.toString())) {
                                numeroDePieza = Integer.valueOf(botones[x][y].getText()); //anotamos elnumero de casilla
                                posX = x; //guardamos la poscision x de la casilla
                                posY = y; //guardamos la posicion y de la casilla
                                break principal;
                            }
                            break;
                        case 2:
                            if (botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                                numeroDePieza = Integer.valueOf(botones[x][y].getText()); //anotamos elnumero de casilla
                                posX = x; //guardamos la poscision x de la casilla
                                posY = y; //guardamos la posicion y de la casilla
                                break principal;
                            }
                            break;
                    }
                }
            }
        }
        //mandamos a mover la pieza en el numero de casilla que se consiguio
        moverPieza(suma, numeroDePieza, posX, posY);
    }

    public void moverPieza(int suma, int numeroDePieza, int posXPiezaVieja, int posYPiezaVieja) {
        int numeroPiezaHaciaMoverInt = suma + numeroDePieza; //la suma dara el numero de casilla a donde trasladaremos la pieza
        String numeroPiezaHaciaMover = String.valueOf(numeroPiezaHaciaMoverInt); //este sera el texto que evaluarmos en cada tirada
        System.out.println(numeroPiezaHaciaMover);
        //aqui quitamos la pieza de su antigua pocision
        if (botones[posXPiezaVieja][posYPiezaVieja].getIcon().toString().equals(ambas.toString())) { //este if evalua si las pezas estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(numeroPiezaHaciaMover)) { //este if ve si la piza existe en el tablero
                        switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                            case 1:
                                botones[posXPiezaVieja][posYPiezaVieja].setIcon(amarilla);
                                botones[x][y].setIcon(roja);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                            case 2:
                                botones[posXPiezaVieja][posYPiezaVieja].setIcon(roja);
                                botones[x][y].setIcon(amarilla);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                        }
                    } //si la casilla no existe entonces pasa turno
                }
            }
        } else { //si el if no se cumple entonces las piezas no estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(numeroPiezaHaciaMover)) { //con los iteradores del for vemos si el numero de pieza existe en el tablero
                        //este if ve si ya hay una pieza ocupando la casilla
                        if (botones[x][y].getIcon().toString().equals(roja.toString()) || botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                            botones[x][y].setIcon(ambas);
                            saberFuncionalidadDeCasilla(x, y);
                            asiganarImagenABoton(posXPiezaVieja, posYPiezaVieja);
                        } else {
                            switch (turno) {
                                case 1:
                                    botones[x][y].setIcon(roja);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(posXPiezaVieja, posYPiezaVieja);
                                    break;
                                case 2:
                                    botones[x][y].setIcon(amarilla);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(posXPiezaVieja, posYPiezaVieja);
                                    break;
                            }
                        }
                    }//si no cumple el if entonces pasa turno
                }
            }
        }
    }

    /**
     * Este metodo evalua la casilla en la matriz de Casillas dentro de tablero
     * ve su funcinalidad y a partir de eso da una imagen
     *
     * @param x
     * @param y
     */
    public void asiganarImagenABoton(int x, int y) {
        switch (tablero.getMiTablero()[x][y].getComportamiento()) {

            case "pierdeturno":
                botones[x][y].setIcon(pierdeTurno);
                break;
            case "tiradados":
                botones[x][y].setIcon(dados);
                break;
            case "avanza":
                botones[x][y].setIcon(avanza);
                break;
            case "retrocede":
                botones[x][y].setIcon(retrocede);
                break;
            case "subida":
                botones[x][y].setIcon(subida);
                break;
            case "bajada":
                botones[x][y].setIcon(bajada);
                break;
            default:
                botones[x][y].setIcon(caritaFeliz);
                break;
        }
    }

    public void saberFuncionalidadDeCasilla(int x, int y) {
        switch (tablero.getMiTablero()[x][y].getComportamiento()) {
            case "pierdeturno":
                pierdeTurno();
                break;
            case "tiradados":
                tiraDados();
                break;
            case "avanza":
                avanza(x, y);
                break;
            case "retrocede":
                retrocede(x, y);
                break;
            case "subida":
                subidaOBajada(x, y);
                break;
            case "bajada":
                subidaOBajada(x, y);
                break;
        }
    }

    public void pierdeTurno() {
        if (turno == 1) {
            jugadorSinTurno = 1;
        } else if (turno == 2) {
            jugadorSinTurno = 2;
        }
    }

    public void tiraDados() {
        if (turno == 1) {
            jugadorSinTurno = 1;
        } else if (turno == 2) {
            jugadorSinTurno = 2;
        }
    }

    public void avanza(int viejaX, int viejaY) {
        int numeroDeCasillaActual = Integer.valueOf(botones[viejaX][viejaY].getText());
        int suma = tablero.getMiTablero()[viejaX][viejaY].getCantidadDePosiciones() + numeroDeCasillaActual;
        String casillaNueva = String.valueOf(suma);
        //aqui quitamos la pieza de su antigua pocision
        if (botones[viejaX][viejaY].getIcon().toString().equals(ambas.toString())) { //este if evalua si las pezas estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //este if ve si la piza existe en el tablero
                        switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                            case 1:
                                botones[viejaX][viejaY].setIcon(amarilla);
                                botones[x][y].setIcon(roja);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                            case 2:
                                botones[viejaX][viejaY].setIcon(roja);
                                botones[x][y].setIcon(amarilla);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                        }
                    } //si la casilla no existe entonces pasa turno
                }
            }
        } else { //si el if no se cumple entonces las piezas no estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //con los iteradores del for vemos si el numero de pieza existe en el tablero
                        //este if ve si ya hay una pieza ocupando la casilla
                        if (botones[x][y].getIcon().toString().equals(roja.toString()) || botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                            botones[x][y].setIcon(ambas);
                            asiganarImagenABoton(viejaX, viejaY);
                        } else {
                            switch (turno) {
                                case 1:
                                    botones[x][y].setIcon(roja);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                                case 2:
                                    botones[x][y].setIcon(amarilla);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                            }
                        }
                    }//si no cumple el if entonces pasa turno
                }
            }
        }

    }

    public void retrocede(int viejaX, int viejaY) {
        int numeroDeCasillaActual = Integer.valueOf(botones[viejaX][viejaY].getText());
        int resta = numeroDeCasillaActual - tablero.getMiTablero()[viejaX][viejaY].getCantidadDePosiciones();
        String casillaNueva = String.valueOf(resta);
        //aqui quitamos la pieza de su antigua pocision
        if (botones[viejaX][viejaY].getIcon().toString().equals(ambas.toString())) { //este if evalua si las pezas estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //este if ve si la piza existe en el tablero
                        switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                            case 1:
                                botones[viejaX][viejaY].setIcon(amarilla);
                                botones[x][y].setIcon(roja);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                            case 2:
                                botones[viejaX][viejaY].setIcon(roja);
                                botones[x][y].setIcon(amarilla);
                                saberFuncionalidadDeCasilla(x, y);
                                break;
                        }
                    } //si la casilla no existe entonces pasa turno
                }
            }
        } else { //si el if no se cumple entonces las piezas no estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //con los iteradores del for vemos si el numero de pieza existe en el tablero
                        //este if ve si ya hay una pieza ocupando la casilla
                        if (botones[x][y].getIcon().toString().equals(roja.toString()) || botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                            botones[x][y].setIcon(ambas);
                            asiganarImagenABoton(viejaX, viejaY);
                        } else {
                            switch (turno) {
                                case 1:
                                    botones[x][y].setIcon(roja);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                                case 2:
                                    botones[x][y].setIcon(amarilla);
                                    saberFuncionalidadDeCasilla(x, y);
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                            }
                        }
                    }//si no cumple el if entonces pasa turno
                }
            }
        }

    }

    public void subidaOBajada(int viejaX, int viejaY) {
        int x = tablero.getMiTablero()[viejaX][viejaY].getFilaFinal();
        int y = tablero.getMiTablero()[viejaX][viejaY].getColumnaFinal();
        if (x < botones.length && y < botones[0].length) {
            if (botones[viejaX][viejaY].getIcon().toString().equals(ambas.toString())) {
                switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                    case 1:
                        botones[viejaX][viejaY].setIcon(amarilla);
                        botones[x][y].setIcon(roja);
                        saberFuncionalidadDeCasilla(x, y);
                        break;
                    case 2:
                        botones[viejaX][viejaY].setIcon(roja);
                        botones[x][y].setIcon(amarilla);
                        saberFuncionalidadDeCasilla(x, y);
                        break;
                }
            } else {
                //este if ve si ya hay una pieza ocupando la casilla
                if (botones[x][y].getIcon().toString().equals(roja.toString()) || botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                    botones[x][y].setIcon(ambas);
                    asiganarImagenABoton(viejaX, viejaY);
                } else {
                    switch (turno) {
                        case 1:
                            botones[x][y].setIcon(roja);
                            saberFuncionalidadDeCasilla(x, y);
                            asiganarImagenABoton(viejaX, viejaY);
                            break;
                        case 2:
                            botones[x][y].setIcon(amarilla);
                            saberFuncionalidadDeCasilla(x, y);
                            asiganarImagenABoton(viejaX, viejaY);
                            break;
                    }
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelDado1;
    private javax.swing.JLabel labelDado2;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables
}
