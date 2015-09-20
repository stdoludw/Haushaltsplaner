<?php


class Model_Einkauf {
	private $mintAnzahl;
	private $mstrDatum;
	private $mintE_ID;
	
	private $mstrBetrag;
	private $mstrK_Name;
	private $mstrBLZ;
	private $mstrKnr;
	private $mstrMin;
	private $mintK_ID;
	
	private $mstrP_Name;
	private $mintGewicht;
	private $mfltPreis;
	private $mintP_ID;
	
	private  $mstrM_Name;
	private  $mstrPLZ;
	private  $mstrAdr;
	private  $mintEntfernung;
	private  $mintM_ID;
        
        private $mstrPasswort;
	
	function __construct(
			$pintAnzahl, $pstrDatum,$E_ID,
			$pstrP_Name, $pstrGewicht, $pfltPreis, $P_ID,
			$pstrM_Name, $pstrPlz, $pstrAdr, $pstrEntfernung, $M_ID,
			$pstrK_Name, $pstrBetrag, $pstrBLZ, $pstrKnr, $pstrMin,$K_ID, $pstrPasswort) 
	
	{
		$this->mintAnzahl = $pintAnzahl;
		$this->mstrDatum = $pstrDatum;
		$this->mintE_ID = $E_ID;
		
		$this->mstrM_Name = $pstrM_Name;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintM_ID = $M_ID;
		
		$this->mstrP_Name = $pstrP_Name;
		$this->mintGewicht = $pstrGewicht;
		$this->mfltPreis = $pfltPreis;
		$this->mintP_ID = $P_ID;
		
		$this->mstrBetrag = exec("java aes $pstrPasswort $pstrBetrag Encode");
		$this->mstrK_Name =  exec("java aes $pstrPasswort $pstrK_Name Encode");
		$this->mstrBLZ =  exec("java aes $pstrPasswort $pstrBLZ Encode");
		$this->mstrKnr =  exec("java aes $pstrPasswort $pstrKnr Encode");
		$this->mstrMin =  exec("java aes $pstrPasswort $pstrMin Encode");
		$this->mintK_ID =  $K_ID;
		$this->mstrPasswort = $pstrPasswort;
			
		
	}

	function array_return() {

		return array(
                $this->mintAnzahl,
		 $this->mstrDatum,
		 $this->mintE_ID,
		
		 $this->mintM_ID ,
		 $this->mstrM_Name,
		 $this->mstrPLZ,
		 $this->mstrAdr,
		 $this->mintEntfernung,
		
		 $this->mintP_ID,
		 $this->mstrP_Name,
		 $this->mintGewicht,
		 $this->mfltPreis,
		
		 $this->mintK_ID,
		 $this->mstrK_Name,
		 $this->mstrBLZ,
		 $this->mstrKnr,
		 $this->mstrBetrag,
		 $this->mstrMin);
			
	}
	
	function eintragen() {
		return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values($this->mintAnzahl, $this->mstrDatum, $this->mintK_ID, $this->mintP_ID, $this->mintM_ID;)";
	}
	function update() {
		return "update Einkauf set anzahl = $this->mintAnzahl,set datum = $this->mstrDatum, set k_ID = $this->mintK_ID, set p_ID = $this->mintP_ID, set m_ID = $this->mintM_ID where E_ID = $this->mintE_ID);";
	}
}
