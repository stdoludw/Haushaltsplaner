<?php

echo "<h2>Einkauf</h2>";

echo "<tr>";
echo "<th> Anzahl</th>";
echo "<th> Datum</th>";
echo "<th> E_ID </th>";

echo "<th> M_ID</th>";
echo "<th> Marktname</th>";
echo "<th> Postleizahl </th>";
echo "<th> Adresse</th>";
echo "<th> Entfernung</th>";

echo "<th> P_ID</th>";
echo "<th> Produktname</th>";
echo "<th> Gewicht</th>";
echo "<th> Preis</th>";

echo "<th> K_ID</th>";
echo "<th> Kontoname</th>";
echo "<th> Bankleitzahl </th>";
echo "<th> Kontonummer</th>";
echo "<th> Betrag</th>";
echo "<th> Minimum</th>";

echo "</tr>";


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

		echo "<tr>";
		echo "<td> .$this->mintAnzahl. ID</td>";
		echo "<td> .$this->mstrDatum. ID</td>";
		echo "<td> .$this->mintE_ID. ID</td>";
		
		echo "<td> .$this->mintM_ID. ID</td>";
		echo "<td> .$this->mstrM_Name. Name</td>";
		echo "<td> .$this->mstrPLZ. Postleizahl</td>";
		echo "<td> .$this->mstrAdr. Adresse</td>";
		echo "<td> .$this->mintEntfernung. Entfernung</td>";
		
		echo "<td> .$this->mintP_ID,. ID</td>";
		echo "<td> .$this->mstrP_Name. Name</td>";
		echo "<td> .$this->mintGewicht. Gewicht </td>";
		echo "<td> .$this->mfltPreis. Preis</td>";
		
		echo "<td> .$this->mintK_ID. ID</th>";
		echo "<td> .$this->mstrK_Name. Name</th>";
		echo "<td> .$this->mstrBLZ. Bankleitzahl </th>";
		echo "<td> .$this->mstrKnr. Kontonummer</th>";
		echo "<td> .$this->mstrBetrag. Betrag</th>";
		echo "<td> .$this->mstrMin. Minimum</th>";
		echo "</tr>";
		
		
	}
	
	function eintragen() {
		return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values($this->mintAnzahl, $this->mstrDatum, $this->mkntKonto, $this->mprdProdukt, $this->mmkrMarkt;)";
	}
	function update() {
		return "update Einkauf set anzahl = $this->mintAnzahl,set datum = $this->mstrDatum, set k_ID = $this->mkntKonto, set p_ID = $this->mprdProdukt, set m_ID = $this->mmkrMarkt where E_ID = $this->mintID);";
	}
}
?>