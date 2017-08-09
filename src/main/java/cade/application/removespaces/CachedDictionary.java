package cade.application.removespaces;

import java.util.ArrayList;

public class CachedDictionary {

	ArrayList<String> dicionarioReserva = new ArrayList<String>();

	public CachedDictionary(){
		dicionarioReserva.add("atp");
		dicionarioReserva.add("adenosine");
		dicionarioReserva.add("triphosphate");
		dicionarioReserva.add("disodium");
		dicionarioReserva.add("salt");
		dicionarioReserva.add("hydrate");
		dicionarioReserva.add("dissídio");
		dicionarioReserva.add("99 %");
		dicionarioReserva.add("hplc");
		dicionarioReserva.add("crystalline");
		dicionarioReserva.add("from");
		dicionarioReserva.add("fórmula");//TEM NO DICONARIO ABERTO MAS O PROGRAMA NAO ACHA.
		dicionarioReserva.add("c10h14n5na2o13p3");//MAS QUE POR@# EH ESSA?
		dicionarioReserva.add("xh2o");// X QTDE DE AGUA??
		dicionarioReserva.add("peso");//ESTRANHAMENTE NAO TEM NO DICIONARIO ABERTO
	}

	public void addToCache(String word) {
		dicionarioReserva.add(word);
	}

	public boolean contains(String word) {
		return dicionarioReserva.contains(word);
	};
}
