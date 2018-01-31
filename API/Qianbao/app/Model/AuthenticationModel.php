<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;
use DB;
use Illuminate\Support\Facades\Hash;
class AuthenticationModel extends Model
{
    public static function register($username,$password,$email){
        $hashPassword = Hash::make($password);
        $insert = DB::table("users")->insertGetId(
           [
               'name' => $username,
               'password' => $hashPassword,
               'email' => $email,
               'created_at' => date('Y-m-d H:i:s'),
               'updated_at' => date('Y-m-d H:i:s')
           ]
        );

        $getData = DB::table("users")->where("id",$insert)->first();

        return $getData;
    }

    public static function login(){

    }

    public static function getHashPassword($email){
        $getPassword = DB::table("users")->where("email",$email)->first();
        return $getPassword->password;
    }

}
