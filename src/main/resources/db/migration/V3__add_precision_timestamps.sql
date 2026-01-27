alter table users
    alter column created_at type timestamp(2) WITHOUT TIME ZONE using created_at::timestamp(2) WITHOUT TIME ZONE;

alter table users
    alter column deleted_at type timestamp(2) WITHOUT TIME ZONE using deleted_at::timestamp(2) WITHOUT TIME ZONE;

alter table users
    alter column updated_at type timestamp(2) WITHOUT TIME ZONE using updated_at::timestamp(2) WITHOUT TIME ZONE;
