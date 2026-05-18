package de.ts.btin2.blogic;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VorwahlDao_Impl implements VorwahlDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vorwahl> __insertionAdapterOfVorwahl;

  private final EntityDeletionOrUpdateAdapter<Vorwahl> __deletionAdapterOfVorwahl;

  public VorwahlDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVorwahl = new EntityInsertionAdapter<Vorwahl>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `vorwahlen` (`vorwahl`,`ort`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vorwahl entity) {
        if (entity.getVorwahl() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getVorwahl());
        }
        if (entity.getOrt() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOrt());
        }
      }
    };
    this.__deletionAdapterOfVorwahl = new EntityDeletionOrUpdateAdapter<Vorwahl>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vorwahlen` WHERE `vorwahl` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vorwahl entity) {
        if (entity.getVorwahl() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getVorwahl());
        }
      }
    };
  }

  @Override
  public void insert(final Vorwahl ort) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVorwahl.insert(ort);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Vorwahl ort) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfVorwahl.handle(ort);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Vorwahl> find(final String suchbegriff) {
    final String _sql = "SELECT * FROM vorwahlen WHERE vorwahl LIKE ? OR ort LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (suchbegriff == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, suchbegriff);
    }
    _argIndex = 2;
    if (suchbegriff == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, suchbegriff);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVorwahl = CursorUtil.getColumnIndexOrThrow(_cursor, "vorwahl");
      final int _cursorIndexOfOrt = CursorUtil.getColumnIndexOrThrow(_cursor, "ort");
      final List<Vorwahl> _result = new ArrayList<Vorwahl>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Vorwahl _item;
        final String _tmpVorwahl;
        if (_cursor.isNull(_cursorIndexOfVorwahl)) {
          _tmpVorwahl = null;
        } else {
          _tmpVorwahl = _cursor.getString(_cursorIndexOfVorwahl);
        }
        final String _tmpOrt;
        if (_cursor.isNull(_cursorIndexOfOrt)) {
          _tmpOrt = null;
        } else {
          _tmpOrt = _cursor.getString(_cursorIndexOfOrt);
        }
        _item = new Vorwahl(_tmpVorwahl,_tmpOrt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
