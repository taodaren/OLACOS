package net.osplay.data.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import net.osplay.data.bean.Community;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMMUNITY".
*/
public class CommunityDao extends AbstractDao<Community, Void> {

    public static final String TABLENAME = "COMMUNITY";

    /**
     * Properties of entity Community.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property AUTOGRAPH = new Property(0, String.class, "AUTOGRAPH", false, "AUTOGRAPH");
        public final static Property BACKGROUND = new Property(1, String.class, "BACKGROUND", false, "BACKGROUND");
        public final static Property CREATEDATE = new Property(2, String.class, "CREATEDATE", false, "CREATEDATE");
        public final static Property CREATEID = new Property(3, String.class, "CREATEID", false, "CREATEID");
        public final static Property HEADID = new Property(4, String.class, "HEADID", false, "HEADID");
        public final static Property ID = new Property(5, String.class, "ID", false, "ID");
        public final static Property INTRODUCTION = new Property(6, String.class, "INTRODUCTION", false, "INTRODUCTION");
        public final static Property ISDELETE = new Property(7, String.class, "ISDELETE", false, "ISDELETE");
        public final static Property ISEXAMINE = new Property(8, String.class, "ISEXAMINE", false, "ISEXAMINE");
        public final static Property NAME = new Property(9, String.class, "NAME", false, "NAME");
        public final static Property PHOTO = new Property(10, String.class, "PHOTO", false, "PHOTO");
        public final static Property REASON = new Property(11, String.class, "REASON", false, "REASON");
        public final static Property REGION = new Property(12, String.class, "REGION", false, "REGION");
    }


    public CommunityDao(DaoConfig config) {
        super(config);
    }
    
    public CommunityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMMUNITY\" (" + //
                "\"AUTOGRAPH\" TEXT," + // 0: AUTOGRAPH
                "\"BACKGROUND\" TEXT," + // 1: BACKGROUND
                "\"CREATEDATE\" TEXT," + // 2: CREATEDATE
                "\"CREATEID\" TEXT," + // 3: CREATEID
                "\"HEADID\" TEXT," + // 4: HEADID
                "\"ID\" TEXT," + // 5: ID
                "\"INTRODUCTION\" TEXT," + // 6: INTRODUCTION
                "\"ISDELETE\" TEXT," + // 7: ISDELETE
                "\"ISEXAMINE\" TEXT," + // 8: ISEXAMINE
                "\"NAME\" TEXT," + // 9: NAME
                "\"PHOTO\" TEXT," + // 10: PHOTO
                "\"REASON\" TEXT," + // 11: REASON
                "\"REGION\" TEXT);"); // 12: REGION
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMMUNITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Community entity) {
        stmt.clearBindings();
 
        String AUTOGRAPH = entity.getAUTOGRAPH();
        if (AUTOGRAPH != null) {
            stmt.bindString(1, AUTOGRAPH);
        }
 
        String BACKGROUND = entity.getBACKGROUND();
        if (BACKGROUND != null) {
            stmt.bindString(2, BACKGROUND);
        }
 
        String CREATEDATE = entity.getCREATEDATE();
        if (CREATEDATE != null) {
            stmt.bindString(3, CREATEDATE);
        }
 
        String CREATEID = entity.getCREATEID();
        if (CREATEID != null) {
            stmt.bindString(4, CREATEID);
        }
 
        String HEADID = entity.getHEADID();
        if (HEADID != null) {
            stmt.bindString(5, HEADID);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(6, ID);
        }
 
        String INTRODUCTION = entity.getINTRODUCTION();
        if (INTRODUCTION != null) {
            stmt.bindString(7, INTRODUCTION);
        }
 
        String ISDELETE = entity.getISDELETE();
        if (ISDELETE != null) {
            stmt.bindString(8, ISDELETE);
        }
 
        String ISEXAMINE = entity.getISEXAMINE();
        if (ISEXAMINE != null) {
            stmt.bindString(9, ISEXAMINE);
        }
 
        String NAME = entity.getNAME();
        if (NAME != null) {
            stmt.bindString(10, NAME);
        }
 
        String PHOTO = entity.getPHOTO();
        if (PHOTO != null) {
            stmt.bindString(11, PHOTO);
        }
 
        String REASON = entity.getREASON();
        if (REASON != null) {
            stmt.bindString(12, REASON);
        }
 
        String REGION = entity.getREGION();
        if (REGION != null) {
            stmt.bindString(13, REGION);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Community entity) {
        stmt.clearBindings();
 
        String AUTOGRAPH = entity.getAUTOGRAPH();
        if (AUTOGRAPH != null) {
            stmt.bindString(1, AUTOGRAPH);
        }
 
        String BACKGROUND = entity.getBACKGROUND();
        if (BACKGROUND != null) {
            stmt.bindString(2, BACKGROUND);
        }
 
        String CREATEDATE = entity.getCREATEDATE();
        if (CREATEDATE != null) {
            stmt.bindString(3, CREATEDATE);
        }
 
        String CREATEID = entity.getCREATEID();
        if (CREATEID != null) {
            stmt.bindString(4, CREATEID);
        }
 
        String HEADID = entity.getHEADID();
        if (HEADID != null) {
            stmt.bindString(5, HEADID);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(6, ID);
        }
 
        String INTRODUCTION = entity.getINTRODUCTION();
        if (INTRODUCTION != null) {
            stmt.bindString(7, INTRODUCTION);
        }
 
        String ISDELETE = entity.getISDELETE();
        if (ISDELETE != null) {
            stmt.bindString(8, ISDELETE);
        }
 
        String ISEXAMINE = entity.getISEXAMINE();
        if (ISEXAMINE != null) {
            stmt.bindString(9, ISEXAMINE);
        }
 
        String NAME = entity.getNAME();
        if (NAME != null) {
            stmt.bindString(10, NAME);
        }
 
        String PHOTO = entity.getPHOTO();
        if (PHOTO != null) {
            stmt.bindString(11, PHOTO);
        }
 
        String REASON = entity.getREASON();
        if (REASON != null) {
            stmt.bindString(12, REASON);
        }
 
        String REGION = entity.getREGION();
        if (REGION != null) {
            stmt.bindString(13, REGION);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Community readEntity(Cursor cursor, int offset) {
        Community entity = new Community( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // AUTOGRAPH
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // BACKGROUND
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CREATEDATE
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // CREATEID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // HEADID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // INTRODUCTION
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ISDELETE
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // ISEXAMINE
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // NAME
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // PHOTO
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // REASON
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // REGION
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Community entity, int offset) {
        entity.setAUTOGRAPH(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBACKGROUND(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCREATEDATE(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCREATEID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHEADID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setINTRODUCTION(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setISDELETE(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setISEXAMINE(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNAME(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPHOTO(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setREASON(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setREGION(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Community entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Community entity) {
        return null;
    }

    @Override
    public boolean hasKey(Community entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
