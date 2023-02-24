<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class RoleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        DB::table('roles')->insert([
            'name' => 'User Admin',
            'description' => 'User Admin - full control',
            'created_at' => now()
        ]);
        DB::table('roles')->insert([
            'name' => 'Moderator',
            'description' => 'Moderator - can edit/delete posts',
            'created_at' => now()
        ]);
        DB::table('roles')->insert([
            'name' => 'Theme Manager',
            'description' => 'Theme Manager - can edit themes',
            'created_at' => now()
        ]);

    }
}
