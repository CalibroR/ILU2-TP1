package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
        this.marche = new Marche(nbEtals);

	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtals) {
	        this.etals = new Etal[nbEtals];
	        for (int i = 0; i < nbEtals; i++) {
                etals[i] = new Etal();
            }
		}
		
		public int trouverEtalLibre() {
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].getVendeur() == null) { 
		            return i; 
		        }
		    }
		    return -1; 
		}
		
		public Etal[] trouverEtals(String produit) {
	        int count = 0;
	        for (Etal etal : etals) {
	            if (etal.getProduit().equals(produit)) {
	                count++;
	            }
	        }
	        if (count == 0) {
	            return new Etal[0];
	        }
	        Etal[] result = new Etal[count];
	        int index = 0;
	        for (Etal etal : etals) {
	            if (etal.getProduit().equals(produit)) {
	                result[index++] = etal;
	            }
	        }

	        return result;
	    }
		
		public Etal trouverVendeur(Gaulois gaulois) {
	        for (Etal etal : etals) {
	            if (etal.getVendeur() != null && etal.getVendeur().equals(gaulois)) {
	                return etal; 
	            }
	        }
	        return null; 
	    }
	}
	
	
		
	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
        if (indiceEtal >= 0 && indiceEtal < marche.etals.length) {
            Etal etal = marche.etals[indiceEtal];
            etal.setVendeur(vendeur);
            etal.setProduit(produit);
            etal.setNbProduit(nbProduit);
        } else {
            System.out.println("L'indice de l'étal est invalide.");
        }
    }
		        
	public class Etal {
        private Gaulois vendeur;
        private String produit;
        private int nbProduit;

        public Etal() {
            this.vendeur = null;
            this.produit = "";
            this.nbProduit = 0;
        }

        public Gaulois getVendeur() {
            return vendeur;
        }

        public void setVendeur(Gaulois vendeur) {
            this.vendeur = vendeur;
        }

        public String getProduit() {
            return produit;
        }

        public void setProduit(String produit) {
            this.produit = produit;
        }

        public int getNbProduit() {
            return nbProduit;
        }

        public void setNbProduit(int nbProduit) {
            this.nbProduit = nbProduit;
        }
    }
	
}

