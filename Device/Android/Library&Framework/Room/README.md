# Room 기본개념

Room은 Jetpack에서 제공하는 라이브러리로,  기존 Cursor 단위로 통신하는 쿼리를 객체 단위로 통신할 수 있도록 돕습니다.

이를 ORM(Object Relational Mapping) 이라 합니다. 말 그대로 DB의 객체를 Java or Kotlin으로 Mapping 해주는 역할을 합니다. 



Google이 권장하는 강력한 라이브러리로, ROOM은 SQLite를 활용하여, DB 엑세스 지원을 위한 추상화 계층을 SQLite에 제공합니다. 
또한 LiveData와 RxJava를 위한 Observable 객체도 제공합니다.

MVVM 패턴의 Model 역할을 담당합니다.



<img src="https://user-images.githubusercontent.com/22608825/102683628-b7c7c080-4215-11eb-96dc-e95294a79357.png" alt="image" style="zoom: 67%;" />



## 구성요소

Room의 주요 구성요소 입니다.

### Entity

> DB의 Table을 Java or Kotlin Class로 나타낸 것을 의미합니다. DataModel Class.
>
> - Project package 구조 : data.db.entity 

```kotlin
@Entity(tableName="Book")
@TypeConverters(DataConverter::class)
data class Book(
	@PrimaryKey(autoGenreate=true) val id:Int,
	@ColumnInfo(name="title") val title:String,
	@ColumnInfo(name="description") val description:String,
	@Ignore val ib:Boolean)
	

===============================
class DateConverter {
    @TypeConverter
    fun toDate(value: Long): Date = Date(value)

    @TypeConverter
    fun toLong(date: Date): Long = date.time
}
```

| Anotation       | 설명                                                         |
| --------------- | ------------------------------------------------------------ |
| @Entity         | Entity 임을 적용하는 Anotation.<br />tableName=Table 이름    |
| @TypeConverters | Table에서 읽은 값의 특정 Type을 원하는 Type으로 Convert 하기 위해 작성하는 Anotation.<br />필수는 아니며, 속성값은 Converter 클래스를 지정하면된다. Converter Class는 별도로 상속한 것이 없다.<br />보통 long 을 시간으로 변경하는 등에 사용. |
| @PrimaryKey     | Primary Key를 지정하기 위한 Anotation.<br />autoGenreate의 경우 값을 자동생성하는 속성이다. |
| @ColumnInfo     | Column 임을 지정하는 Anotation.                              |
| @Ignore         | DB에 저장하지 않고 Application 동작에만 필요한 멤버변수를 지정하는 Anotation.<br />이 Anotation을 지정하면 DB에 저장되지 않습니다. |

  

### Dao

> Database Access Object 로, 많이 들어본 Dao랑 동일합니다. DB에 접근하는 객체로 데이터 조작을 수행하는 메소드가 작성된 Class입니다.

```kotlin
@Dao
interface BookDao {
	@Query("SELECT * FROM Book ORDER BY id ASC")
	fun findAll(): DataSource.Factory<Int, Book>
	
    @Query("SELECT title FROM Book WHERE id=:id")
    fun getTitle(id:Int):String
    
	@Insert(onConflict=OnConflickStrategy.REPLACE)
	fun insert(book:Book)
	
	@Delete
	fun delete(book:Book)
}
```



| Anotation | 설명                                                         |
| --------- | ------------------------------------------------------------ |
| @Dao      | Dao Class를 지정하기 위한 Anotation.                         |
| @Query    | 직접 Query를 작성할 수 있는 Anotation.<br />:컬럼명 를 이용해 Query에 Param을 지정할 수 있다. |
| @Insert   | 데이터 삽입을 위한 Anotation.<br />onConflict 속성은 이미 저장된 데이터가 있을 경우 어떻게 할지 지정한다. |
| @Delete   | 데이터 삭제를 위한 Anotation.                                |



### Database

> database holder를 포함하며, App에 영구 저장되는 데이터와 기본 열결을 위한 주 엑세스 지점입니다.

```kotlin
@Database(entities ={Book.class}, version=DB_VERSION, exportSchema=false)
abstract class BookDataBase : RoomDatabase {
	abstract fun getBookDao():BookDao
    
    companion object {
        const val DB_VERSION = 1
    }
}
```

| Anotation | 설명 |
| --------- | ---- |
| @Database |      |

