package io.kindstrom.senderremote.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.data.ApplicationTestCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RepositoryImplTest extends ApplicationTestCase {

    private final int ID_INDEX = 0;
    private final int NAME_INDEX = 123;

    @Mock
    private SQLiteDatabase db;
    @Mock
    private Cursor cursor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void map() throws Exception {
        List<Row> expectedRows = new ArrayList<>();
        expectedRows.add(new Row(1, "name 1"));
        expectedRows.add(new Row(5, "name 5"));
        expectedRows.add(new Row(1236, "just another name"));

        when(cursor.getCount()).thenReturn(expectedRows.size());
        when(cursor.moveToNext())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(cursor.getInt(ID_INDEX))
                .thenReturn(expectedRows.get(0).id)
                .thenReturn(expectedRows.get(1).id)
                .thenReturn(expectedRows.get(2).id);
        when(cursor.getString(NAME_INDEX))
                .thenReturn(expectedRows.get(0).name)
                .thenReturn(expectedRows.get(1).name)
                .thenReturn(expectedRows.get(2).name);

        RepositoryImpl<Row> repository = new RepositoryTestClass(db);
        assertEquals(expectedRows, repository.map(cursor));
    }

    private class RepositoryTestClass extends RepositoryImpl<Row> {

        RepositoryTestClass(SQLiteDatabase db) {
            super(db);
        }

        @Override
        public Row mapSingle(Cursor cursor) {
            int id = cursor.getInt(ID_INDEX);
            String name = cursor.getString(NAME_INDEX);

            return new Row(id, name);
        }
    }

    private class Row {
        final int id;
        final String name;

        private Row(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Row row = (Row) o;

            return id == row.id && (name != null ? name.equals(row.name) : row.name == null);

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}