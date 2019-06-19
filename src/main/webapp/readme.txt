2.设计实体
	Category
		private String id;
		private String name;
		private String description;

		create table category
		(
			id varchar(40) primary key,
			name varchar(40) not null unique,
			description varchar(255)
		);

	Book
		private String id;
		private String name;
		private double price;
		private String author;
		private String image;  //记住书的图片的位置
		private String description;
		private Category category;  // 书与分类的关系是 多对一；

		create table book
		(
			id varchar(40) primary key,
			name varchar(40) not null unique,
			price decimal(8,2) not null,
			author varchar(40) not null,
			image varchar(255) not null,
			description varchar(255),
			category_id varchar(40) not null,
			constraint category_id_FK foreign key(category_id) references category(id)
		);



	Orders
		private String id;
		private Date ordertime;  //下单时间
		private boolean state;   //订单状态
		private double price;    //订单总价

		private User user;    //记住下单人    用户和订单的关系是 一对多 ，显示订单需要显示用户
		private Set orderitems;   //记住订单所有的订单项   订单与订单项的关系是 一对多， 显示订单需要显示订单项

		create table orders
		(
			id varchar(40) primary key,
			ordertime datetime not null,
			state boolean not null,
			price decimal(8,2) not null,
			user_id varchar(40),
			constraint user_id_FK foreign key(user_id) references user(id)
		);


	OrderItem
		private String id;
		private int quantity; //书的数目
		private double price;  //订单项总价
		private Book book;   //记住订单项代表的是哪本书
								//订单和书的关系是 多对一 显示订单需要显示书；
		//private Orders order; //订单项不会单独显示，所以不需存储订单的对象
								//但在数据库中还是需要绑定关系

		create table orderitem
		(
			id varchar(40) primary key,
			quantity int not null,
			price decimal(8,2) not null,
			book_id varchar(40),
			constraint book_id_FK foreign key(book_id) references book(id),
			orders_id varchar(40),
			constraint orders_id_FK foreign key(orders_id) references orders(id)
		);



	User
		private String id;
		private String username;
		private String password;
		private String phone;
		private String cellphone;
		private String email;
		private String address;

		create table user
		(
			id varchar(40) primary key,
			username varchar(40) not null unique,
			password varchar(40) not null,
			phone varchar(40) not null,
			cellphone varchar(40) not null,
			email varchar(40) not null,
			address varchar(255) not null
		);



3.设计表
	create table user
	(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
		phone varchar(20) not null,
		cellphone varchar(20) not null,
		email varchar(40) not null,
		address varchar(255) not null
	);

	create table category
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		description varchar(255),
		isDel number(1) not null default 0,
	);

	create table book
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		price decimal(8,2) not null,
		author varchar(40) not null,
		publicationDate date not null,
		image varchar(255) not null,
		description varchar(255),
		category_id varchar(40) not null default 0,
		constraint category_id_FK foreign key(category_id) references category(id)
	);

	create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		state boolean not null,
		price decimal(8,2) not null,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)

	)	;

	create table orderitem
	(
		id varchar(40) primary key,
		quantity int not null,
		price decimal(8,2) not null,
		book_id varchar(40),
		constraint book_id_FK foreign key(book_id) references book(id),
		order_id varchar(40),
		constraint order_id_FK foreign key(order_id) references orders(id)
	);

	create table privilege
	(
		id varchar(40) primary key,
		name varchar(40),
		description varchar(255)
	);

	create table user_privilege
	(
		user_id varchar(40),
		privilege_id varchar(40)
	);