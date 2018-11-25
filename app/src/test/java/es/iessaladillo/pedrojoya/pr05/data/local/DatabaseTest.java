package es.iessaladillo.pedrojoya.pr05.data.local;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNull;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = Database.getInstance();
    }

    @Test
    public void shouldGetInstanceReturnNotNull() {
        assertNotNull(Database.getInstance());
    }

    @Test
    public void shouldQueryAvatarsReturnNotNullWhenNoAvatar() {
        database.setUsers(Collections.emptyList());

        List<User> users = database.queryAvatars();

        assertNotNull(users);
    }

    @Test
    public void shouldQueryAvatarsReturnData() {
        User user1 = new User(0, "Test1", email, phoneNumber, address, web);
        User user2 = new User(0, "Test2", email, phoneNumber, address, web);
        List<User> data = Arrays.asList(user1, user2);
        database.setUsers(data);

        List<User> users = database.queryAvatars();

        assertThat(users, IsIterableContainingInOrder.contains(user1, user2));
    }

    @Test
    public void shouldGetDefaultAvatarReturnNullWhenNoData() {
        database.setUsers(Collections.emptyList());

        User defaultUser = database.getDefaultAvatar();

        assertNull(defaultUser);
    }

    @Test
    public void shouldGetDefaultAvatarReturnFirstAvatar() {
        User user1 = new User(0, "Test1", email, phoneNumber, address, web);
        User user2 = new User(0, "Test2", email, phoneNumber, address, web);
        List<User> data = Arrays.asList(user1, user2);
        database.setUsers(data);

        User defaultUser = database.getDefaultAvatar();

        assertEquals(user1, defaultUser);
    }

    @Test
    public void shouldGetRandomAvatarReturnNullWhenNoData() {
        database.setUsers(Collections.emptyList());

        User randomUser = database.getRandomAvatar();

        assertNull(randomUser);
    }

    @Test
    public void shouldGetRandomAvatarReturnAvatar() {
        User user1 = new User(0, "Test1", email, phoneNumber, address, web);
        User user2 = new User(0, "Test2", email, phoneNumber, address, web);
        List<User> data = Arrays.asList(user1, user2);
        database.setUsers(data);

        User randomUser = database.getRandomAvatar();

        assertThat(data, hasItem(randomUser));
    }


    @Test
    public void shouldQueryAvatarReturnNullWhenNotFound() {
        database.setUsers(Collections.emptyList());
        User user1 = new User(0, "Test1", email, phoneNumber, address, web);
        User user2 = new User(0, "Test2", email, phoneNumber, address, web);
        database.insertAvatar(user1);
        database.insertAvatar(user2);

        User user = database.queryAvatar(3);

        assertNull(user);
    }

    @Test
    public void shouldQueryAvatarReturnTheAvatar() {
        database.setUsers(Collections.emptyList());
        User user1 = new User(0, "Test1", email, phoneNumber, address, web);
        User user2 = new User(0, "Test2", email, phoneNumber, address, web);
        database.insertAvatar(user1);
        database.insertAvatar(user2);

        User user = database.queryAvatar(2);

        assertEquals(user2, user);
    }

}
