<?php
echo "<h2>Markt</h2>";

echo "<tr>";
echo "<th> ID</th>";
echo "<th> Name</th>";
echo "<th> Postleizahl </th>";
echo "<th> Adresse</th>";
echo "<th> Entfernung</th>";
echo "</tr>";

class Markt {
	private $mstrName;
	private $mstrPLZ;
	private $mstrAdr;
	private $mintEntfernung;
	private $mintID;
	
	function Markt($pstrName, $pstrPlz, $pstrAdr, $pstrEntfernung, $pintID) {
		$this->mstrName = $pstrName;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintID = $pintID;
	}
	
	function ausgabe() {
		echo "<tr>";
		echo "<td> .$this->mintID. ID</td>";
		echo "<td> .$this->mstrName. Name</td>";
		echo "<td> .$this->mstrPLZ. Postleizahl</td>";
		echo "<td> .$this->mstrAdr. Adresse</td>";
		echo "<td> .$this->mintEntfernung. Entfernung</td>";
		echo "</tr>";
	}
	
	function eintragen() {
		return "insert into Markt (name,postleitzahl,adresse,entfernung) Values('$this->mstrName', '$this->mstrPLZ', '$this->mstrAdr', $this->mintEntfernung);";
	}
	function update() {
		return "update Markt set name = '$this->mstrName' , postleitzahl = '$this->mstrPLZ' ,
		adresse = $this->mstrAdr' , entfernung = $this->mintEntfernung where M_ID = $this->mintID ;";
	}
}

?>