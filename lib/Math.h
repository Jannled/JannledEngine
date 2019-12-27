#ifndef JMATH_H
#define JMATH_H

class Mat4f
{
	public:
		float *values;

		Mat4f() {
			values[0]  = 1;
			values[1]  = 0;
			values[2]  = 0;
			values[3]  = 0;
			values[4]  = 0;
			values[5]  = 1;
			values[6]  = 0;
			values[7]  = 0;
			values[8]  = 0;
			values[9]  = 0;
			values[10] = 1;
			values[11] = 0;
			values[12] = 0;
			values[13] = 0;
			values[14] = 0;
			values[15] = 1;
		};

		Mat4f(float (&values)[16]) 
		{
			this->values = values;
		}

		~Mat4f(){
			
		};

		float &operator[](int pos)
		{
			return values[pos];
		}

		Mat4f multiply(Mat4f a, Mat4f b)
		{
			Mat4f result;

			result[0]  = a[0] * b[0] + a[1] * b[4] + a[2] * b[8] + a[3] * b[12];
			result[1]  = a[0] * b[1] + a[1] * b[5] + a[2] * b[9] + a[3] * b[13];
			result[2]  = a[0] * b[2] + a[1] * b[6] + a[2] * b[10] + a[3] * b[14];
			result[3]  = a[0] * b[3] + a[1] * b[7] + a[2] * b[11] + a[3] * b[15];

			result[4]  = a[4] * b[0] + a[5] * b[4] + a[6] * b[8] + a[7] * b[12];
			result[5]  = a[4] * b[1] + a[5] * b[5] + a[6] * b[9] + a[7] * b[13];
			result[6]  = a[4] * b[2] + a[5] * b[6] + a[6] * b[10] + a[7] * b[14];
			result[7]  = a[4] * b[3] + a[5] * b[7] + a[6] * b[11] + a[7] * b[15];

			result[8]  = a[8] * b[0] + a[9] * b[4] + a[10] * b[8] + a[11] * b[12];
			result[9]  = a[8] * b[1] + a[9] * b[5] + a[10] * b[9] + a[11] * b[13];
			result[10] = a[8] * b[2] + a[9] * b[6] + a[10] * b[10] + a[11] * b[14];
			result[11] = a[8] * b[3] + a[9] * b[7] + a[10] * b[11] + a[11] * b[15];

			result[12] = a[12] * b[0] + a[13] * b[4] + a[14] * b[8] + a[15] * b[12];
			result[13] = a[12] * b[1] + a[13] * b[5] + a[14] * b[9] + a[15] * b[13];
			result[14] = a[12] * b[2] + a[13] * b[6] + a[14] * b[10] + a[15] * b[14];
			result[15] = a[12] * b[3] + a[13] * b[7] + a[14] * b[11] + a[15] * b[15];

			return result;
		}

		Mat4f multiply(Mat4f b)
		{
			return multiply(*this, b);
		}

		static Mat4f translate(float posX, float posY, float posZ)
		{
			return Mat4f();
		}
};

class Vec3
{
	public:
		float values[3] = {0, 0, 0};

		Vec3() {

		}

		~Vec3() {

		}
};

#endif //JMATH_H
