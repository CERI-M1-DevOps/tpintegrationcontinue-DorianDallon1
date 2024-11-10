package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * @return the size of the list
     */
    public long getSize() {
        return size;
    }

    /**
     * Adds a new element to the head of the list.
     * @param element the element to add
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la valeur du premier l ment qui a pour valeur {@code element}.
     * Si aucun l ment n'a cette valeur, la liste n'est pas modifi e.
     * @param element la valeur cherch e
     * @param nouvelleValeur la nouvelle valeur
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie la valeur de tous les l ments qui ont pour valeur {@code element}.
     * Si aucun l ment n'a cette valeur, la liste n'est pas modifi e.
     * @param element la valeur cherch e
     * @param nouvelleValeur la nouvelle valeur
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une repr sentation textuelle de la liste sous la forme
     * "ListeSimple( n1, n2, ... , nk)".
     * @return une cha ne repr sentant la liste
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier l ment qui a pour valeur {@code element}. Si la liste
     * est vide ou si aucun l ment n'a cette valeur, la liste n'est pas modifi e.
     * @param element la valeur cherch e
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les l ments qui ont pour valeur {@code element}. Si la liste
     * est vide ou si aucun l ment n'a cette valeur, la liste n'est pas modifi e.
     * @param element la valeur cherch e
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime tous les l ments qui ont pour valeur {@code element} dans la
     * liste commen ant   {@code tete}. Si la liste est vide ou si aucun l ment
     * n'a cette valeur, la liste n'est pas modifi e. La taille de la liste est
     * mise   jour.
     * @param element la valeur cherch e
     * @param tete la t te de la liste
     * @return la nouvelle t te de la liste
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Returns the second-to-last node in the list.
     * If the list is empty or contains only one element, returns null.
     * @return the second-to-last node, or null if not applicable
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des l ments de la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Returns the node that precedes the given node r in the list.
     * @param r the node whose predecessor is to be found
     * @return the node that precedes r, or null if r is the first element of the list
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Changes the positions of two nodes in the list.
     * If both nodes are not the head of the list, the predecessor of each node
     * is found and its successor is changed to the other node.
     * If one of the nodes is the head of the list, the head is changed to the other node.
     * Finally, the successors of the two nodes are swapped.
     * @param r1 the first node to exchange
     * @param r2 the second node to exchange
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1 = null;
        Noeud precedentR2 = null;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            if (precedentR1 != null) {
                precedentR1.setSuivant(r2);
            }
            if (precedentR2 != null) {
                precedentR2.setSuivant(r1);
            }
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            if (precedentR2 != null) {
                precedentR2.setSuivant(r1);
            }
            tete = r2;
        }
        else if (r2 == tete) {
            precedentR1 = getPrecedent(r1);
            if (precedentR1 != null) {
                precedentR1.setSuivant(r2);
            }
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}
