package fr.insa.rennes.web.resource;

import fr.insa.rennes.web.model.Album;
import fr.insa.rennes.web.model.PlayerCard;
import io.swagger.annotations.Api;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("api/album")
@Api(value = "album")
public class AlbumResource {
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	private final EntityManager em;

	// We give (inject) the database using the constructor of the resource
	// So: the service does not have to manage the database (not its business)
	@Inject
	public AlbumResource(final EntityManager em) {
		super();
		this.em = em;
	}


	@POST
	@Path("album")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postAlbum() {
		final Album newAlbum = new Album();
		final EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(newAlbum);
			tr.commit();
			LOGGER.info("Album created: " + newAlbum);

			return Response.status(Response.Status.OK).entity(newAlbum).build();
		}catch(final RollbackException | IllegalStateException ex) {
			if(tr.isActive()) {
				tr.rollback();
			}
			LOGGER.log(Level.SEVERE, "Crash on adding an album: " + newAlbum, ex);
			// A Web exception is then thrown.
			throw new WebApplicationException(HttpURLConnection.HTTP_BAD_REQUEST);
		}
	}

	@GET
	@Path("album/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Album getAlbumByID(@PathParam("id") final int id) {
		final Album album = em.find(Album.class, id);
		if(album == null) {
			throw new WebApplicationException("no album", HttpURLConnection.HTTP_BAD_REQUEST);
		}
		return album;
	}

	@PUT
	@Path("album/card/{id}/{idCard}")
	public void addCardInAlbum(@PathParam("id") final int idAlbum, @PathParam("idCard") final int idCard) {
		final Album album = em.find(Album.class, idAlbum);
		final PlayerCard card = em.find(PlayerCard.class, idCard);

		if(album == null || card == null) {
			throw new WebApplicationException("incorrect resources", HttpURLConnection.HTTP_BAD_REQUEST);
		}

		if(album.getCards().contains(card)) {
			throw new WebApplicationException("This album already has this card", HttpURLConnection.HTTP_BAD_REQUEST);
		}

		album.addCard(card);
	}
}
