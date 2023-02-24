<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class Role_UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //
        DB::table('role_user')->insert([
            'user_id' => 1,
            'role_id' => 1,
            'created_at' => now()
        ]);//this is a test user
        DB::table('role_user')->insert([
            'user_id' => 2,
            'role_id' => 1,
            'created_at' => now()
        ]);//jane
        DB::table('role_user')->insert([
            'user_id' => 3,
            'role_id' => 2,
            'created_at' => now()
        ]);//bob
        DB::table('role_user')->insert([
            'user_id' => 4,
            'role_id' => 3,
            'created_at' => now()
        ]);//susan
    }
}
