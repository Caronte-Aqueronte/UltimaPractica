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
    private ImageIcon roja = new ImageIcon(piezaRojaImagen.getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
    private ImageIcon amarilla = new ImageIcon(piezaAmarillaImagen.getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
    private ImageIcon ambas = new ImageIcon(ambasPiezasImagen.getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
    //
    private boolean banderaPieza = false;
    private HiloNumeroAleatorio hilo;
    private int turno = 1;
    private int contadorPasaTurno = 0;
    private boolean perdioTurnoElAnterior = false;

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
        //iniciamos el cronometro
        Cronometro cronometro = new Cronometro(labelMins, labelSegs, labelMili);
        cronometro.start();
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

    /*
    Manda a traer el nombre segun el turno
    ademas setea el texto de la label correspondient
     */
    public void saberNombreDelJugadorDeTurno(int turno) {
        labelNombre.setText(jugadores.get(turno - 1).getNombre());
    }

    /**
     * Este metodo evalua si hay alguna pieza en la ultima casilla de la matriz
     * de botones si es asi entonces manda a sobresescribir los jugadores
     *
     * @throws IOException
     */
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

    /**
     * Este metodo es el encargado de sobre escribir las partidas ganadas,
     * jugadas y pedidas de ada jugador, ademas manda a sobreescribir l archivo
     * binario
     *
     * @param idGanador
     * @param idPerdedor
     * @throws IOException
     */
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
        labelMins = new javax.swing.JLabel();
        labelMili = new javax.swing.JLabel();
        labelSegs = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelQUeCasilla = new javax.swing.JLabel();
        labelPenalizacion = new javax.swing.JLabel();

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

        labelMins.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        labelMins.setText("0");

        labelMili.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        labelMili.setText("0");

        labelSegs.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        labelSegs.setText("0");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Tiempo:");

        labelQUeCasilla.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        labelPenalizacion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelQUeCasilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelPenalizacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(49, 49, 49)
                                .addComponent(labelMins)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(labelSegs)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel8)
                                .addGap(29, 29, 29)
                                .addComponent(labelMili))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(labelDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(labelDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMins, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMili, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSegs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDado1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDado2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelQUeCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//este metodo se activa cada que el jugador da enter
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && banderaPieza == false) { //este if activa una bandera para saber si ya se preciono una vez el enter
            hilo = new HiloNumeroAleatorio("Hilo numero aleatorio", labelDado1, labelDado2); //si es primera vez que se activa entonces declara un hilo
            banderaPieza = true; //vuelve la bandera true
            hilo.start(); //comienza el hilo
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER && banderaPieza == true) {
            banderaPieza = false; //vulve bandera false para ser utilizada de nuevo
            hilo.detener(); //detiene el hilo
            int suma = Integer.valueOf(labelDado1.getText()) + Integer.valueOf(labelDado2.getText()); //suma los numeros obtenidos en las labels
            rastrearPieza(suma);//manda la suma y rastrea la pieza segun el turno
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
            if (perdioTurnoElAnterior == true) {
                contadorPasaTurno++;
                if (contadorPasaTurno >= 2) {
                    contadorPasaTurno = 0;
                    perdioTurnoElAnterior = false;
                    if (turno == 1) {
                        turno = 2;
                    }
                    if (turno == 2) {
                        turno = 1;
                    }
                }
            }
            saberNombreDelJugadorDeTurno(turno);
        }
    }//GEN-LAST:event_formKeyPressed
    /**
     * Este metodo busca la pieza segun el turno pues 1 es rojo y 2 es amarillo
     * entonces guarda el texto del borton pues sera el identificador
     *
     * @param suma
     */
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
                        labelQUeCasilla.setText("El jugador anterior avanzo hacia la casilla " + numeroPiezaHaciaMover);
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
                        labelQUeCasilla.setText("El jugador anterior avanzo hacia la casilla " + numeroPiezaHaciaMover);
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

    /**
     * Este metodo da las funcionalidades de la casilla dependiendo su posicion
     * en el arrglo
     *
     * @param x
     * @param y
     */
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
                labelPenalizacion.setText("El jugador anterior subio");
                subidaOBajada(x, y);
                break;
            case "bajada":
                labelPenalizacion.setText("El jugador anterior bajo");
                subidaOBajada(x, y);
                break;
            default:
                labelPenalizacion.setText("");
                break;
        }
    }

    /**
     * Este metodo indica quien piede el turno para que cada que hay un nuevo
     * tecleo se sepa
     */
    public void pierdeTurno() {
        labelPenalizacion.setText("El jugador anterior pierde un turno");
        perdioTurnoElAnterior = true;
    }

    /**
     * Este metodo indica quien piede el turno para que cada que hay un nuevo
     * tecleo se sepa
     */
    public void tiraDados() {
        labelPenalizacion.setText("El jugador anterior vuelve a tirar los dados");
        perdioTurnoElAnterior = true;
    }

    public void avanza(int viejaX, int viejaY) {

        int numeroDeCasillaActual = Integer.valueOf(botones[viejaX][viejaY].getText());
        int suma = tablero.getMiTablero()[viejaX][viejaY].getCantidadDePosiciones() + numeroDeCasillaActual;
        String casillaNueva = String.valueOf(suma);
        labelPenalizacion.setText("El jugador anterior avanzo " + String.valueOf(tablero.getMiTablero()[viejaX][viejaY].getCantidadDePosiciones()) + " casillas mas");
        //aqui quitamos la pieza de su antigua pocision
        if (botones[viejaX][viejaY].getIcon().toString().equals(ambas.toString())) { //este if evalua si las pezas estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //este if ve si la piza existe en el tablero
                        switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                            case 1:
                                botones[viejaX][viejaY].setIcon(amarilla);
                                botones[x][y].setIcon(roja);
                                break;
                            case 2:
                                botones[viejaX][viejaY].setIcon(roja);
                                botones[x][y].setIcon(amarilla);
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
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                                case 2:
                                    botones[x][y].setIcon(amarilla);
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
        System.out.println("Se tiene que mover a " + casillaNueva);
        labelPenalizacion.setText("El jugador anterior retrocedio " + String.valueOf(tablero.getMiTablero()[viejaX][viejaY].getCantidadDePosiciones()) + " casillas");
        //aqui quitamos la pieza de su antigua pocision
        if (botones[viejaX][viejaY].getIcon().toString().equals(ambas.toString())) { //este if evalua si las pezas estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //este if ve si la piza existe en el tablero
                        switch (turno) { //si existe entonces quitamos la pieza del jugador y solo dejamos la contrincante
                            case 1:
                                botones[viejaX][viejaY].setIcon(amarilla);
                                botones[x][y].setIcon(roja);
                                break;
                            case 2:
                                botones[viejaX][viejaY].setIcon(roja);
                                botones[x][y].setIcon(amarilla);
                                break;
                        }
                    } //si la casilla no existe entonces pasa turno
                }
            }
        } else { //si el if no se cumple entonces las piezas no estan juntas
            for (int x = 0; x < botones.length; x++) {
                for (int y = 0; y < botones[x].length; y++) {
                    if (botones[x][y].getText().equals(casillaNueva)) { //con los iteradores del for vemos si el numero de pieza existe en el tablero
                        System.out.println("entro");
                        //este if ve si ya hay una pieza ocupando la casilla
                        if (botones[x][y].getIcon().toString().equals(roja.toString()) || botones[x][y].getIcon().toString().equals(amarilla.toString())) {
                            botones[x][y].setIcon(ambas);
                            asiganarImagenABoton(viejaX, viejaY);
                        } else {
                            switch (turno) {
                                case 1:
                                    botones[x][y].setIcon(roja);
                                    asiganarImagenABoton(viejaX, viejaY);
                                    break;
                                case 2:
                                    System.out.println("se tuvo que poner amarilllo la " + x + y);
                                    botones[x][y].setIcon(amarilla);
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
                        break;
                    case 2:
                        botones[viejaX][viejaY].setIcon(roja);
                        botones[x][y].setIcon(amarilla);
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
                            asiganarImagenABoton(viejaX, viejaY);
                            break;
                        case 2:
                            botones[x][y].setIcon(amarilla);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelDado1;
    private javax.swing.JLabel labelDado2;
    private javax.swing.JLabel labelMili;
    private javax.swing.JLabel labelMins;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPenalizacion;
    private javax.swing.JLabel labelQUeCasilla;
    private javax.swing.JLabel labelSegs;
    // End of variables declaration//GEN-END:variables
}
