<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //a user has a name, email, password, and role
        DB::table('users')->insert([
            'name' => 'Test User',
            'email' => 'test@test.com',
            'password' => bcrypt('password'),
            'created_at' => now()
        ]);
        //we need to add jane useradmin
//        bob moderator
        //susan themeadmin
        DB::table('users')->insert([
            'name' => 'Jane Useradmin',
            'email' => 'jane@example.com',
            'password' => bcrypt('password')
        ]);
        DB::table('users')->insert([
            'name' => 'Bob Moderator',
            'email' => 'bob@example.com',
            'password' => bcrypt('password')
        ]);
        DB::table('users')->insert([
            'name' => 'Susan Themeadmin',
            'email' => 'susan@example.com',
            'password' => bcrypt('password')
        ]);


    }
}
