using HimuMusic.Services.ServiceComponents;

namespace HimuMusic.Services
{
    public interface IHimuMusicApiService
    {
        public Task<HttpServerResponse<T>> GetAsync<T>(string api);

        public Task<HttpServerResponse<T>> PostAsJsonAsync<T, U>(string api, U data);

        public Task<HttpServerResponse> PostAsJsonAsync<T>(string api, T data);

        public Task<HttpServerResponse> PostAsync(string api,HttpContent data);
    }
}
