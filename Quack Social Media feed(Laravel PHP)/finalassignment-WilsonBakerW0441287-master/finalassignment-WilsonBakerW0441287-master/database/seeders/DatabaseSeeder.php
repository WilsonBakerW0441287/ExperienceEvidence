<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {

        //add the seeders to run here
        $this->call([
            RoleSeeder::class,
            UserSeeder::class,
            Role_UserSeeder::class,
            PostSeeder::class,
            ThemeSeeder::class
        ]);
    }
}
