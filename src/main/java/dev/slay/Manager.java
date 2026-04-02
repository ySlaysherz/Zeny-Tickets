package dev.slay;

import java.util.HashSet;
import java.util.Set;

public class Manager {

    public static Set<Ticket> TicketsCriados = new HashSet<>();

    public static void addTicket(Ticket ticket) {
        TicketsCriados.add(ticket);
    }
    public static void removerTicket(Ticket ticket) {
        TicketsCriados.remove(ticket);
    }
    public static Set<Ticket> getTickets() {
        return TicketsCriados;
    }
    public static Ticket getTicket(int id) {
        for (Ticket ticket : TicketsCriados) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }
}
