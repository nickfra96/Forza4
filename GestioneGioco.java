package homework;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GestioneGioco {
	
	public GestioneGioco(){}


	public boolean aggiungiCoin(Bottoni[][] bottoni, int[][] tabellaGioco,int c, int coin){
		
		if(tabellaGioco[0][c]!=0)
			return false;
		else{
		boolean coin_inserito=false;
		for(int i=tabellaGioco.length-1; i>=0;i--)
			if(!coin_inserito)
				if(tabellaGioco[i][c]==0){
				cambiaIcona(coin, bottoni[i][c]);
				coin_inserito=true;
				tabellaGioco[i][c]=coin;
				}
		return true;
	}
	}
	
	private  void cambiaIcona(int g, JButton bottone){
		if(g==2){
			bottone.setIcon(new ImageIcon(getClass().getResource("forza_quadrato_verde.png")));
		}
		else if(g==1)
				bottone.setIcon(new ImageIcon(getClass().getResource("forza_quadrato_arancione.png")));
		
	}
	
	
	public int controllaOrizz(int[][] tabellaGioco){
		for(int[] r : tabellaGioco){ 
			int cont=0;
			for(int i=0; i<r.length-1; i++){
				if(r[i]==r[i+1] && r[i]!=0) {
					cont++;
				    if (cont==3){
				    	System.out.println("player: "+ r[i]);
				    	return r[i];
				    }
				}
				else cont=0;
			}
		}
			return -1;
	}
	
	
	public int controllaVert(int[][] tabellaGioco){
		for(int j=0; j<tabellaGioco[0].length; j++){
			int cont=0;
			for(int i=0; i<tabellaGioco.length-1; i++){
				if(tabellaGioco[i][j]==tabellaGioco[i+1][j] && tabellaGioco[i][j]!=0){
					cont++;
					if(cont==3) {
						System.out.println("player "+tabellaGioco[i][j]);
						return tabellaGioco[i][j];
					}
				}
				else cont=0;
			}
		}
		return -1;
	}
	
	public int controllaDiagDX(int[][] tabellaGioco){
		for(int i=0; i<tabellaGioco.length-3; i++)
			for( int j=0; j<tabellaGioco[0].length-3; j++){
				int cont=0;
				for(int z=1; z<4; z++)
					if(tabellaGioco[i][j]==tabellaGioco[i+z][j+z] && tabellaGioco[i][j]!=0){ 
						cont++;
						if(cont==3){
							System.out.println("player "+tabellaGioco[i][j]);
							return tabellaGioco[i][j];
						}
					}
					else cont=0;
				
			}
		return -1;
	
		
	
	}
	
	public int controllaDiagSX(int[][] tabellaGioco){
		for(int i=0; i<tabellaGioco.length-3; i++){ 
			for(int j=3; j<tabellaGioco[0].length; j++){
				int cont=0;
				for(int z=1; z<4; z++)
					if (tabellaGioco[i][j]==tabellaGioco[i+z][j-z] && tabellaGioco[i][j]!=0){
						cont++;
						if(cont==3){
							System.out.println("player "+tabellaGioco[i][j]);
							return tabellaGioco[i][j];
						}
					}
					else cont=0;
			
		}
		
	}
		return -1;
	}
	
	public boolean parita(int[][] tabellaGioco){
		for (int[] r : tabellaGioco)
			for(int i=0; i<r.length; i++)
				if (r[i]==0) {
					return false;
				}
		System.out.println("PARI OK");
		return true;
				
	}
	
	
	
	
}
	
		
	
