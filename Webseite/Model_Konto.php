<?php

class Model_Konto {

    private $mstrBetrag;
    private $mstrName;
    private $mstrBLZ;
    private $mstrKnr;
    private $mstrMin;
    private $mintID;
    private $mstrPasswort;

    function __construct($pstrName, $pstrBetrag, $pstrBLZ, $pstrKnr, $pstrMin, $pintID, $pstrPasswort) {

        // ausführen von aes
        $this->mstrBetrag = exec("java aes $pstrPasswort $pstrBetrag Encode");
        $this->mstrName = exec("java aes $pstrPasswort $pstrName Encode");
        $this->mstrBLZ = exec("java aes $pstrPasswort $pstrBLZ Encode");
        $this->mstrKnr = exec("java aes $pstrPasswort $pstrKnr Encode");
        $this->mstrMin = exec("java aes $pstrPasswort $pstrMin Encode");
        $this->mintID = $pintID;
        $this->mstrPasswort = $pstrPasswort;
    }

    function array_return() {
        return array($this->mintID,
            $this->mstrName,
            $this->mstrBLZ, $this->mstrKnr,
            $this->mstrBetrag, $this->mstrMin);
    }

    function eintragen() {
        $name = exec("java aes $this->mstrPasswort $this->mstrName Decode");
        $betrag = exec("java aes $this->mstrPasswort $this->mstrBetrag Decode");
        $blz = exec("java aes $this->mstrPasswort $this->mstrBLZ Decode");
        $knr = exec("java aes $this->mstrPasswort $this->mstrKnr Decode");
        $min = exec("java aes $this->mstrPasswort $this->mstrMin Decode");
        return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values('$name', '$blz', '$knr','$betrag','$min');";
    }

    function update() {

        $name = exec("java aes $this->mstrPasswort $this->mstrName Decode");
        $betrag = exec("java aes $this->mstrPasswort $this->mstrBetrag Decode");
        $blz = exec("java aes $this->mstrPasswort $this->mstrBLZ Decode");
        $knr = exec("java aes $this->mstrPasswort $this->mstrKnr Decode");
        $min = exec("java aes $this->mstrPasswort $this->mstrMin Decode");

        return "update Konto set name = '$name' ,bankleitzahl = '$blz', kontonummer = '$knr' ,betrag = '$betrag',minimum = '$min' where K_ID = $this->mintID;";
    }

}
