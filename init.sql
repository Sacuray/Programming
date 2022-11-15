create table coordinates(
    id serial primary key,
    x real,
    y real
);

create table car(
    name text primary key,
    cool bool
);

create table my_user(
    id serial primary key,
    username text,
    password text,
    online bool
);

create table human_being(
    id serial primary key,
    key int,
    name text,
    coordinates_id int references coordinates(id),
    creation_date date,
    real_hero bool,
    has_toothpick bool,
    impact_speed real,
    weapon_type text,
    mood text,
    car_id text references car(name),
    user_id int references my_user(id)
);
