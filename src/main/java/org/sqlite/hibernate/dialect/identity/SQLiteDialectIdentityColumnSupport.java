package org.sqlite.hibernate.dialect.identity;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteDialectIdentityColumnSupport extends IdentityColumnSupportImpl {
	@Override
	public boolean supportsIdentityColumns() {
		return true;
	}

	@Override
	public boolean supportsInsertSelectIdentity() {
		return true; // https://sqlite.org/lang_returning.html
	}

	@Override
	public boolean hasDataTypeInIdentityColumn() {
		// As specified in NHibernate dialect
		// FIXME true
		return false;
	}

	@Override
	public String appendIdentitySelectToInsert(String insertString) {
		// FIXME does not work when PK is not an alias of rowid (ie not INTEGER PRIMARY KEY)
		return insertString + " RETURNING rowid";
	}

	@Override
	public String getIdentitySelectString(String table, String column, int type) {
		return "SELECT last_insert_rowid()";
	}

	@Override
	public String getIdentityColumnString(int type) {
		// return "integer primary key autoincrement";
		// FIXME "autoincrement"
		return "integer";
	}
}
