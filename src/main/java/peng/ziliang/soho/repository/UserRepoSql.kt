package peng.ziliang.soho.repository

class UserRepoSql {

    companion object {
        const val CUSTOM_SQL = """
            select * from `user` where tid='100'
        """
    }
}