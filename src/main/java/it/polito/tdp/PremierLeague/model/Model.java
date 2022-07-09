package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	List <Match> partiteGrafo;
	private Graph<Match, DefaultWeightedEdge> grafo; 
	HashSet<Action> azioni;
	HashMap <Integer, Match> idMap = new HashMap<Integer, Match>();
	List<Coppie> listaArchi;
	
	public Model() {
		
		dao = new PremierLeagueDAO();
		
	}
	
	public String creaGrafo(int mese, int min) {
		
		partiteGrafo = new ArrayList<Match>(dao.listPartite(mese));
		for(Match m : partiteGrafo)
			idMap.put(m.getMatchID(), m);
		
		
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, partiteGrafo);
		
		listaArchi = new LinkedList<Coppie>(dao.listCoppie(mese, min, idMap));
		
		for(Coppie c : listaArchi)
			Graphs.addEdge(this.grafo, c.getM1(), c.getM2(), c.getPeso());
		
		
		String output = "GRAFO CREATO" + "\n" + "Numero vertici: " + this.grafo.vertexSet().size() + 
						"\nNumero Archi: " + this.grafo.edgeSet().size();
		
		return output;
		
	}
	
	public List<Coppie> getConnessioneMax(){
		
		List <Coppie> lista = new LinkedList<Coppie>();
		
		int max = listaArchi.get(0).getPeso();
		
		for(Coppie p : listaArchi)
			if(p.getPeso() == max)
				lista.add(p);
		
		return lista;
		
	}

	
}
