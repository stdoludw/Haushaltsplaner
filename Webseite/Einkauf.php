<?php
class Einkauf {
	private $mintAnzahl;
	private $mstrDatum;
	private $mkntKonto;
	private $mprdProdukt;
	private $mmkrMarkt;
	private $mintID;
	function Einkauf($pintAnzhal, $pstrDatum, $pkntKonto, $pprdProdukt, $pmkrMarkt, $pintID) {
		$this->mintAnzahl = $pintAnzhal;
		$this->mstrDatum = $pstrDatum;
		$this->mkntKonto = $pkntKonto;
		$this->mmkrMarkt = $pmkrMarkt;
		$this->mprdProdukt = $pprdProdukt;
		$this->mintID = $pintID;
	}
	function ausgabe() {
		return array (
				$this->mintAnzahl,
				$this->mstrDatum,
				$this->mkntKonto,
				$this->mmkrMarkt,
				$this->mprdProdukt,
				$this->mintID 
		);
	}
	function eintragen() {
		return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values($this->mintAnzahl, $this->mstrDatum, $this->mkntKonto, $this->mprdProdukt, $this->mmkrMarkt;)";
	}
	function update() {
		return "update Einkauf set anzahl = $this->mintAnzahl,set datum = $this->mstrDatum, set k_ID = $this->mkntKonto, set p_ID = $this->mprdProdukt, set m_ID = $this->mmkrMarkt where E_ID = $this->mintID);";
	}
}
?>