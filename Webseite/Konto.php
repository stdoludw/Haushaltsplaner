<?php



class Konto {
	private $mstrBetrag;
	private $mstrName;
	private $mstrBLZ;
	private $mstrKnr;
	private $mstrMin;
	private $mintID;
	private $mstrPasswort;
	
	function Konto($pstrName, $pstrBetrag, $pstrBLZ, $pstrKnr, $pstrMin, $pintID, $pstrPasswort) {
		
		// ausführen von aes
		$this->mstrBetrag = exec("java aes $pstrPasswort $mstrBetrag Encode");
		$this->mstrName =  exec("java aes $pstrPasswort $mstrName Encode");
		$this->mstrBLZ =  exec("java aes $pstrPasswort $mstrBLZ Encode");
		$this->mstrKnr =  exec("java aes $pstrPasswort $mstrKnr Encode");
		$this->mstrMin =  exec("java aes $pstrPasswort $mstrMin Encode");
		$this->mintID =  exec("java aes $pstrPasswort $mintID Encode");
		$this->mstrPasswort = $pstrPasswort;
		
		
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
	
	function eintragen()
	{
		$name = exec("java aes $this->mstrPasswort $this->mstrName Decode");
		$betrag = exec("java aes $this->mstrPasswort $this->mstrBetrag Decode");
		$blz = exec("java aes $this->mstrPasswort $this->mstrBLZ Decode");
		$knr = exec("java aes $this->mstrPasswort $this->mstrKnr Decode");
		$min = exec("java aes $this->mstrPasswort $this->mstrMin Decode");
		return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values('$name', '$blz', '$knr','$betrag','$min');";
		
	}
	
	function update()
	{
		
		$name = exec("java aes $this->mstrPasswort $this->mstrName Decode");
		$betrag = exec("java aes $this->mstrPasswort $this->mstrBetrag Decode");
		$blz = exec("java aes $this->mstrPasswort $this->mstrBLZ Decode");
		$knr = exec("java aes $this->mstrPasswort $this->mstrKnr Decode");
		$min = exec("java aes $this->mstrPasswort $this->mstrMin Decode");
		
		return "update Konto set name = '$name' ,bankleitzahl = '$blz', kontonummer = '$knr' ,betrag = '$betrag',minimum = '$min' where K_ID = $this->mintID;";
	}
}
