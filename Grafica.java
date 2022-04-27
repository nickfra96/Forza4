import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Grafica
{	private JFrame frame;
    private int[][] tabellaGioco = new int[6][7];
    private Bottoni[][] bottoni= new Bottoni[6][7];
    private JPanel panel;
    private int coin=1;
    private int vincitore;
    private int giocaDiNuovo=50;
    private JLabel etichettaTurnoIniz= new JLabel ("Benvenuto su Forza4! Inizia il giocatore arancione.");
    
    GestioneGioco gestione = new GestioneGioco();
    private JLabel etichettaTurno;

	public void setCoin(int coin){
		this.coin=coin;
	}
	
	private String getVincitore(int vincitore){
		if(vincitore==1) return "arancione";
		else return "verde";
	}
	
	private void initialize()
	{	
		
    	frame = new JFrame();
		frame.getContentPane().setLayout(null);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setBounds(400, 100, 500, 499);	
		frame.setVisible(true);					
		frame.setTitle("Forza4");
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 484, 411);
		panel.setLayout(new GridLayout(6, 7, 0, 0));
		frame.getContentPane().add(panel);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 414, 484, 46);
		frame.getContentPane().add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JButton restart = new JButton("New button");
		panel2.add(restart, BorderLayout.EAST);
		restart.addActionListener(new acRestart());
		restart.setText("RESTART");
		
		etichettaTurno = etichettaTurnoIniz;
		panel2.add(etichettaTurno, BorderLayout.CENTER);
		for(int i=0; i<bottoni.length; i++)
			for(int j=0; j<bottoni[0].length; j++){
				bottoni[i][j]= new Bottoni(" ");
				panel.add(bottoni[i][j]);
				bottoni[i][j].addActionListener(new click());
				bottoni[i][j].setC(j);
				bottoni[i][j].setR(i);
				bottoni[i][j].setIcon(caricaIcona("forza_quadrato_null.png"));
			}
	

		impostaLF();
						
			
		}
	
	private class acRestart implements ActionListener{
		public void actionPerformed(ActionEvent e){
			giocaDiNuovo = JOptionPane.showConfirmDialog(null,"Hai premuto RESTART. Desideri ricaricare il gioco?","",JOptionPane.YES_NO_OPTION);
			if(giocaDiNuovo==JOptionPane.YES_OPTION) reset();
		}
	}
	
	private void setTurno(){
		if(coin==2)
			etichettaTurno.setText("E' il turno del giocatore verde.");
		else if(coin==1)
			etichettaTurno.setText("E' il turno del giocatore arancione.");
			
	}
	
	private class click implements ActionListener{
		public void actionPerformed (ActionEvent e){
			Bottoni bottone = (Bottoni) e.getSource();
			int colonna= bottone.getC();
			if(gestione.aggiungiCoin(bottoni,tabellaGioco,colonna,coin)){
				
				if(coin==2) setCoin(1); 
				else if (coin==1) setCoin(2);
				
				setTurno();
				if(gestione.controllaOrizz(tabellaGioco)!= -1) {
					vincitore=gestione.controllaOrizz(tabellaGioco);
					finePartita();
				}
				
				else if(gestione.controllaVert(tabellaGioco)!= -1){
					vincitore=gestione.controllaVert(tabellaGioco);
					finePartita();
				}
				else if (gestione.controllaDiagDX(tabellaGioco)!=-1){
					vincitore=gestione.controllaDiagDX(tabellaGioco);
					finePartita();
				}
				else if (gestione.controllaDiagSX(tabellaGioco)!=-1){
					vincitore=gestione.controllaDiagSX(tabellaGioco);
					finePartita();
	
				}
				else if (gestione.parita(tabellaGioco)){
					giocaDiNuovo = JOptionPane.showConfirmDialog(null,"Il gioco � finito in parit�. Vuoi giocare ancora?","",JOptionPane.YES_NO_OPTION);
					if (giocaDiNuovo== JOptionPane.YES_OPTION) reset();
					
					else if(giocaDiNuovo==JOptionPane.NO_OPTION){
						System.exit(0);
					}
				}
			}
			
		}

	}
	
	private void finePartita(){
			giocaDiNuovo= JOptionPane.showConfirmDialog(null,"Il giocatore "+getVincitore(vincitore)+" ha vinto! Vuoi giocare di nuovo?","",JOptionPane.YES_NO_OPTION);
			if (giocaDiNuovo== JOptionPane.YES_OPTION) reset();
			
			else if(giocaDiNuovo==JOptionPane.NO_OPTION){
					System.exit(0);
				}
			else if (giocaDiNuovo==JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
		}
	

		private void reset(){
			 coin=1;
			 vincitore=0;
			 giocaDiNuovo=50;
			    for(int i=0; i<bottoni.length; i++)
				   for(int j=0; j<bottoni[0].length; j++){
					   tabellaGioco[i][j]=0;
					   bottoni[i][j].setIcon(caricaIcona("forza_quadrato_null.png"));
					
				}
			       etichettaTurno.setText("Inizia il giocatore arancione.");
			}
			
	
	private ImageIcon caricaIcona(String nomeFile)
	{	return new ImageIcon(getClass().getResource(nomeFile));
	}
	
	
	private void impostaLF()
	{	try
		{	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{	System.out.println(e);
			System.exit(0);
		}
	}
	
	
    public Grafica()
	{	
    	initialize();
    	
	}
	
	public static void main(String[] args)
	{	new Grafica();
	}
}


