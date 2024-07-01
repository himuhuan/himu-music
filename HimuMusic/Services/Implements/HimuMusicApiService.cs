using HimuMusic.Services.ServiceComponents;
using System.Net;
using System.Net.Http.Json;

namespace HimuMusic.Services.Implements
{
    public class HimuMusicApiService : IHimuMusicApiService
    {
        private readonly HttpClient _httpClient;

        public HimuMusicApiService(HttpClient httpClient)
        {
            _httpClient = httpClient;
            _httpClient.BaseAddress = new Uri("http://localhost:8080");
        }

        public async Task<HttpServerResponse<T>> GetAsync<T>(string api)
        {
            var response = new HttpServerResponse<T>();

            try
            {
                var httpResponse = _httpClient.GetAsync(api).GetAwaiter().GetResult();
                response.StatusCode = (int) httpResponse.StatusCode;
                if (httpResponse.IsSuccessStatusCode)
                {
                    response.Result = await httpResponse.Content.ReadFromJsonAsync<T>();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                response.StatusCode = 500;
            }
            return response;
        }

        public async Task<HttpServerResponse<T>> PostAsJsonAsync<T, U>(string api, U data)
        {
            var response = new HttpServerResponse<T>();

            try
            {
                var httpResponse = await _httpClient.PostAsJsonAsync(api, data);
                response.StatusCode = (int) httpResponse.StatusCode;
                if (httpResponse.IsSuccessStatusCode)
                {
                    if (typeof(T) == typeof(string))
                        response.Result = (T?) (object?) await httpResponse.Content.ReadAsStringAsync();
                    else 
                        response.Result = await httpResponse.Content.ReadFromJsonAsync<T>();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                response.StatusCode = 500;
            }

            return response;
        }

        public async Task<HttpServerResponse> PostAsJsonAsync<T>(string api, T data)
        {
            var response = new HttpServerResponse();

            try
            {
                var httpResponse = await _httpClient.PostAsJsonAsync(api, data);
                response.StatusCode = (int) httpResponse.StatusCode;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                response.StatusCode = 500;
            }

            return response;
        }

        public async Task<HttpServerResponse> PostAsync(string api, HttpContent data)
        {
            var response = new HttpServerResponse();

            try
            {
                var httpResponse = await _httpClient.PostAsync(api, data);
                response.StatusCode = (int) httpResponse.StatusCode;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                response.StatusCode = 500;
            }

            return response;
        }
    }
}
