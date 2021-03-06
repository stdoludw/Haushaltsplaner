<?php


class Einkauf {
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
	
	function Einkauf(
			$pintAnzhal, $pstrDatum,$E_ID,
			$pstrP_Name, $pstrGewicht, $pfltPreis, $P_ID,
			$pstrM_Name, $pstrPlz, $pstrAdr, $pstrEntfernung, $M_ID,
			$pstrK_Name, $pstrBetrag, $pstrBLZ, $pstrKnr, $pstrMin,$K_ID, $pstrPasswort) 
	
	{
		$this->mintAnzahl = $pintAnzhal;
		$this->mstrDatum = $pstrDatum;
		$this->mintE_ID = E_ID;
		
		$this->mstrM_Name = $pstrM_Name;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintM_ID = $M_ID;
		
		$this->mstrP_Name = $pstrP_Name;
		$this->mintGewicht = $pstrGewicht;
		$this->mfltPreis = $pfltPreis;
		$this->mintP_ID = $P_ID;
		
		$this->mstrBetrag = exec("java aes $pstrPasswort $mstrBetrag Encode");
		$this->mstrK_Name =  exec("java aes $pstrPasswort $mstrK_Name Encode");
		$this->mstrBLZ =  exec("java aes $pstrPasswort $mstrBLZ Encode");
		$this->mstrKnr =  exec("java aes $pstrPasswort $mstrKnr Encode");
		$this->mstrMin =  exec("java aes $pstrPasswort $mstrMin Encode");
		$this->mintK_ID =  $K_ID;
		$this->mstrPasswort = $pstrPasswort;
			
		
	}

	function ausgabe() {

		echo "<tr>" ;
		echo "<td> $this->mintAnzahl </td>";
		echo "<td> $this->mstrDatum </td>";
		echo "<td> $this->mintE_ID </td>";
		
		echo "<td> $this->mintM_ID </td>";
		echo "<td> $this->mstrM_Name </td>";
		echo "<td> $this->mstrPLZ </td>";
		echo "<td> $this->mstrAdr </td>";
		echo "<td> $this->mintEntfernung</td>";
		
		echo "<td> $this->mintP_ID</td>";
		echo "<td> $this->mstrP_Name</td>";
		echo "<td> $this->mintGewicht</td>";
		echo "<td> $this->mfltPreis</td>";
		
		echo "<td> $this->mintK_ID</td>";
		echo "<td> $this->mstrK_Name</td>";
		echo "<td> $this->mstrBLZ</td>";
		echo "<td> $this->mstrKnr</td>";
		echo "<td> $this->mstrBetrag</td>";
		echo "<td> $this->mstrMin</td>";
		echo "<td><input type=radio name=radQ3 value=7 id=q7 title=aendern /></td>";
		echo "</tr>" ;
		
		
		
	}
	
	function eintragen() {
		return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values($this->mintAnzahl, $this->mstrDatum, $this->mintK_ID, $this->mintP_ID, $this->mintM_ID;)";
	}
	function update() {
		return "update Einkauf set anzahl = $this->mintAnzahl,set datum = $this->mstrDatum, set k_ID = $this->mintK_ID, set p_ID = $this->mintP_ID, set m_ID = $this->mintM_ID where E_ID = $this->mintE_ID);";
	}
}
?>